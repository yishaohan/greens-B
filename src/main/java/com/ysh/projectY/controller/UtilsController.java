package com.ysh.projectY.controller;

import com.ysh.projectY.utils.JsonResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class UtilsController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> login(HttpServletRequest req, Authentication auth) {
        if (auth == null) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.request", "尚未登录, 请登录!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.request", "当前用户权限不够!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/invalidSession", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> invalidSession(HttpServletRequest req) {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.invalidSession", "会话异常, 请重新登录!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/expiredSession", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> expiredSession(HttpServletRequest req) {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.expiredSession", "会话过期, 请重新登录!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
    }
}
