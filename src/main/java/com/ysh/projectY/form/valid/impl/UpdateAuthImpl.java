package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.entity.Authorization;
import com.ysh.projectY.form.valid.UpdateAuth;
import com.ysh.projectY.service.AuthorizationService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;

public class UpdateAuthImpl implements ConstraintValidator<UpdateAuth, com.ysh.projectY.form.UpdateAuth> {

    private final String[] REQUEST_METHOD = {"-", "GET", "POST", "DELETE", "PUT"};

    AuthorizationService authService;

    public UpdateAuthImpl(AuthorizationService authService) {
        this.authService = authService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.UpdateAuth updateAuth, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        final int parentId = updateAuth.getParentId();
        final int authGrade = updateAuth.getAuthGrade();
        final String authDescript = updateAuth.getAuthDescript();
        final String requestUrl = updateAuth.getRequestUrl();
        final String requestMethod = updateAuth.getRequestMethod();

        if (updateAuth.getId() == updateAuth.getParentId()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.parentId.incorrect-value ").addConstraintViolation();
            return false;
        }
        if (!(authGrade == 1 || authGrade == 2)) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.authGrade.incorrect-value ").addConstraintViolation();
            return false;
        }
        if (authGrade == 1) {
            if (parentId != 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.authGrade.parent-id-incorrect-value ").addConstraintViolation();
                return false;
            }
            updateAuth.setRequestMethod("-");
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
            updateAuth.setAuthDescript("-");
        }
        if (!(requestUrl.startsWith("/api/v1"))) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.requestUrl.incorrect-value ").addConstraintViolation();
            return false;
        }
        if (!Arrays.asList(REQUEST_METHOD).contains(requestMethod)) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.requestMethod.incorrect-value ").addConstraintViolation();
            return false;
        }
        final Optional<Authorization> o_auth = authService.findById(updateAuth.getId());
        if (o_auth.isEmpty()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateAuth.id.not-exist").addConstraintViolation();
            return false;
        }
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
        return true;
    }
}

