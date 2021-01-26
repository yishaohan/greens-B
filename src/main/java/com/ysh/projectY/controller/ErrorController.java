package com.ysh.projectY.controller;

import com.ysh.projectY.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(path = "${projectY.api-base-path}" + "/error")
public class ErrorController {

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    // ???????????????????????????????????????????//
    // 是否需要在response里写入跨域的属性

    @RequestMapping(value = "/401", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error401(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.FORBIDDEN.value(), "projectY.ErrorController.error401", null, req.getServletPath()), HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/403", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error403(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.FORBIDDEN.value(), "projectY.ErrorController.error403", null, req.getServletPath()), HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error404(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "projectY.ErrorController.error404", null, req.getServletPath()), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/500", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error500(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "projectY.ErrorController.error500", null, req.getServletPath()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
