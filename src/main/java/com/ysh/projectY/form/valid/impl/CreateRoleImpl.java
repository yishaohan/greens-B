package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.form.valid.CreateRole;
import com.ysh.projectY.service.RoleService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreateRoleImpl implements ConstraintValidator<CreateRole, com.ysh.projectY.form.CreateRole> {

    RoleService roleService;

    public CreateRoleImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.CreateRole createRole, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (roleService.findByRoleName(createRole.getRoleName()).isPresent()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.CreateRole.RoleName.exist").addConstraintViolation();
            return false;
        }
        if (createRole.getRoleDescript() == null) {
            createRole.setRoleDescript("");
        }
        return true;
    }
}

