package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.service.UserDetailService;
import com.ysh.projectY.form.valid.AddUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddUserImpl implements ConstraintValidator<AddUser, com.ysh.projectY.form.AddUser> {

    UserDetailService userDetailService;

    public AddUserImpl(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.AddUser addUser, ConstraintValidatorContext context) {
        System.out.println("Into: UserValidatorImpl.isValid()");
        // 关闭默认消息
        context.disableDefaultConstraintViolation();
        if (userDetailService.findByUsername(addUser.getUsername()).isPresent()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.user.username.exist").addConstraintViolation();
            return false;
        }
        if (userDetailService.findByMobilePhone(addUser.getMobilePhone()).isPresent()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.user.mobilePhone.exist").addConstraintViolation();
            return false;
        }
        return true;
    }
}

