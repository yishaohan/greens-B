package com.ysh.projectY.valid.impl;

import com.ysh.projectY.entity.SmsCaptchaForm;
import com.ysh.projectY.service.SmsCaptchaService;
import com.ysh.projectY.valid.SmsCaptchaFormValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class SmsCaptchaFormValidatorImpl implements ConstraintValidator<SmsCaptchaFormValidator, SmsCaptchaForm> {

    @Autowired
    SmsCaptchaService smsCaptchaService;

    // register: 注册时需要短信验证码
    //    login: 支持手机号/短信验证码登录
    private final String[] ALL_SOURCE = {"register", "login"};

    @Override
    public boolean isValid(SmsCaptchaForm smsCaptchaForm, ConstraintValidatorContext context) {
        System.out.println("Into: SmsCaptchaValidatorImpl.isValid()");
        // 关闭默认消息
        context.disableDefaultConstraintViolation();
        if (!Arrays.asList(ALL_SOURCE).contains(smsCaptchaForm.getSource())) {
            context.buildConstraintViolationWithTemplate("projectY.valid.SmsCaptcha.source.notExist").addConstraintViolation();
            return false;
        }

        return true;
    }
}

