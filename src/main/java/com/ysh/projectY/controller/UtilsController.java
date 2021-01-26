package com.ysh.projectY.controller;

import com.ysh.projectY.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class UtilsController {

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> login(HttpServletRequest req, HttpServletResponse resp, Authentication auth) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        if (auth == null) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.request", "尚未登录, 请登录!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.request", "当前用户权限不够!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/invalidSession", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> invalidSession(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.invalidSession", "会话异常, 请重新登录!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/expiredSession", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> expiredSession(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNAUTHORIZED.value(), "projectY.UtilsController.login.expiredSession", "会话过期, 请重新登录!", req.getServletPath()), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/submit/test")
    public HttpEntity<?> test() {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.OK.value(), "projectY.UtilsController.test.XXXXXXXX", "测试接口!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/test1", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> test1() {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.OK.value(), "projectY.UtilsController.test1.XXXXXXXX", "测试接口!"), HttpStatus.OK);
    }

}
