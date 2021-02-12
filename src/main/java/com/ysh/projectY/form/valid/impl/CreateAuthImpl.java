package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.entity.Authorization;
import com.ysh.projectY.form.valid.CreateAuth;
import com.ysh.projectY.service.AuthorizationService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;

public class CreateAuthImpl implements ConstraintValidator<CreateAuth, com.ysh.projectY.form.CreateAuth> {

    private final String[] REQUEST_METHOD = {"-", "GET", "POST", "DELETE", "PUT"};

    AuthorizationService authService;

    public CreateAuthImpl(AuthorizationService authService) {
        this.authService = authService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.CreateAuth createAuth, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        final int parentId = createAuth.getParentId();
        final int authGrade = createAuth.getAuthGrade();
        final String authName = createAuth.getAuthName();
        final String authDescript = createAuth.getAuthDescript();
        final String requestUrl = createAuth.getRequestUrl();
        final String requestMethod = createAuth.getRequestMethod();
        final Optional<Authorization> o_parent = authService.findById(parentId);
        if (parentId != 0) {
            if (o_parent.isEmpty()) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.parentId.not-exist").addConstraintViolation();
                return false;
            }
            if (o_parent.get().getAuthGrade() != 1) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.parentId.incorrect-value").addConstraintViolation();
                return false;
            }
        }
        if (!(authGrade == 1 || authGrade == 2)) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.authGrade.incorrect-value").addConstraintViolation();
            return false;
        }
        if (authGrade == 1) {
            if (parentId != 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.authGrade.parent-id-incorrect-value").addConstraintViolation();
                return false;
            }
            createAuth.setRequestMethod("-");
        } else {
            if (parentId == 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.authGrade.parent-id-incorrect-value").addConstraintViolation();
                return false;
            }
            if (requestMethod == null || "".equals(requestMethod) || "-".equals(requestMethod)) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.requestMethod.not-blank").addConstraintViolation();
                return false;
            }
        }
        if (authDescript != null && "".equals(authDescript)) {
            createAuth.setAuthDescript("-");
        }
        if (!(requestUrl.startsWith("/api/v1"))) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.requestUrl.incorrect-value").addConstraintViolation();
            return false;
        }
        if (!Arrays.asList(REQUEST_METHOD).contains(requestMethod)) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.requestMethod.incorrect-value").addConstraintViolation();
            return false;
        }
        final Optional<Authorization> optional = authService.findByAuthName(authName);
        if (optional.isPresent()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.authName.exist").addConstraintViolation();
            return false;
        }
        return true;
    }
}

