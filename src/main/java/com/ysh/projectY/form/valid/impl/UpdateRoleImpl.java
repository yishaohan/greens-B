package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.entity.Role;
import com.ysh.projectY.form.valid.UpdateRole;
import com.ysh.projectY.service.RoleService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UpdateRoleImpl implements ConstraintValidator<UpdateRole, com.ysh.projectY.form.UpdateRole> {

    RoleService roleService;

    public UpdateRoleImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.UpdateRole updateRole, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        final Optional<Role> optional = roleService.findByID(updateRole.getId());
        if (optional.isEmpty()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateRole.id.not-exist").addConstraintViolation();
            return false;
        }
        Role role = optional.get();
        if (!role.getRoleName().equals(updateRole.getRoleName())) {
            final Optional<Role> byRoleName = roleService.findByRoleName(updateRole.getRoleName());
            if (byRoleName.isPresent()) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateRole.roleName.exist").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}

