package com.ysh.projectY.valid.impl;

import com.ysh.projectY.entity.UserRegisterForm;
import com.ysh.projectY.service.UserDetailService;
import com.ysh.projectY.valid.UserRegisterFormValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserRegisterFormValidatorImpl implements ConstraintValidator<UserRegisterFormValidator, UserRegisterForm> {

    UserDetailService userDetailService;

    public UserRegisterFormValidatorImpl(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean isValid(UserRegisterForm userRegisterForm, ConstraintValidatorContext context) {
        System.out.println("Into: UserValidatorImpl.isValid()");
        // 关闭默认消息
        context.disableDefaultConstraintViolation();
        if (userDetailService.findByUsername(userRegisterForm.getUsername()).isPresent()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.user.username.exist").addConstraintViolation();
            return false;
        }
        if (userDetailService.findByMobilePhone(userRegisterForm.getMobilePhone()).isPresent()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.user.mobilePhone.exist").addConstraintViolation();
            return false;
        }
        return true;
    }
}

