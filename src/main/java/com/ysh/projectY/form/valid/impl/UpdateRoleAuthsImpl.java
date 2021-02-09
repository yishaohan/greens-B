package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.entity.Authorization;
import com.ysh.projectY.entity.Role;
import com.ysh.projectY.form.valid.UpdateRoleAuths;
import com.ysh.projectY.service.AuthorizationService;
import com.ysh.projectY.service.RoleService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class UpdateRoleAuthsImpl implements ConstraintValidator<UpdateRoleAuths, com.ysh.projectY.form.UpdateRoleAuths> {

    RoleService roleService;
    AuthorizationService authService;

    public UpdateRoleAuthsImpl(RoleService roleService, AuthorizationService authService) {
        this.roleService = roleService;
        this.authService = authService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.UpdateRoleAuths updateRoleAuths, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        StringBuilder addAuthsDetail = new StringBuilder();
        StringBuilder removeAuthsDetail = new StringBuilder();
        final List<Integer> addAuthIds = updateRoleAuths.getAddAuthIds();
        final List<Integer> removeAuthIds = updateRoleAuths.getRemoveAuthIds();
        final Iterator<Integer> i_addAuthIds = addAuthIds.iterator();
        while (i_addAuthIds.hasNext()) {
            int id = i_addAuthIds.next();
            final Optional<Authorization> optional = authService.findById(id);
            if (optional.isEmpty()) {
                i_addAuthIds.remove();
                addAuthsDetail.append(id).append(", ");
            }
        }
        final Iterator<Integer> i_removeAuthIds = removeAuthIds.iterator();
        while (i_removeAuthIds.hasNext()) {
            int id = i_removeAuthIds.next();
            final Optional<Authorization> optional = authService.findById(id);
            if (optional.isEmpty()) {
                i_removeAuthIds.remove();
                removeAuthsDetail.append(id).append(", ");
            }
        }
        if (!"".equals(addAuthsDetail.toString())) {
            System.out.println("   Add: [ " + addAuthsDetail.substring(0, addAuthsDetail.length() - 2) + " ] Authorization not Exist");
        }
        if (!"".equals(removeAuthsDetail.toString())) {
            System.out.println("Remove: [ " + addAuthsDetail.substring(0, addAuthsDetail.length() - 2) + " ] Authorization not Exist");
        }
        final Optional<Role> optional = roleService.findById(updateRoleAuths.getRoleId());
        if (optional.isEmpty()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateRoleAuths.roleId.not-exist").addConstraintViolation();
            return false;
        }
        return true;
    }
}

