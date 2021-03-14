package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Logs;
import com.ysh.projectY.service.LogsService;
import com.ysh.projectY.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;


@RestController
@RequestMapping(path = "${projectY.api-base-path}" + "/error")
public class ErrorController {

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    final LogsService logsService;

    public ErrorController(LogsService logsService) {
        this.logsService = logsService;
    }

    // ???????????????????????????????????????????//
    // 是否需要在response里写入跨域的属性
    private void createLogs(HttpServletRequest req, HttpServletResponse resp, String source) {
        Logs logs = new Logs();
        String temp;
        logs.setRequestURI(req.getRequestURI());
        temp = req.getQueryString();
        if (temp == null) {
            temp = "";
        }
        logs.setQueryString(temp);
        logs.setMethod(req.getMethod());
        logs.setStatus(String.valueOf(resp.getStatus()));
        logs.setMessage("");
        temp = req.getHeader("referer");
        if (temp == null) {
            temp = "";
        }
        logs.setReferer(temp);
        logs.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
        logs.setThread(Thread.currentThread().getName());
        logs.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        String remoteAddr = req.getHeader("X-Real-IP");
        if (remoteAddr == null) {
            logs.setRemoteAddr(req.getRemoteAddr());
        } else {
            logs.setRemoteAddr(remoteAddr);
        }
        logs.setRemotePort(String.valueOf(req.getRemotePort()));
        temp = req.getHeader("User-Agent");
        if (temp == null) {
            temp = "";
        }
        logs.setUserAgent(temp);
        temp = req.getHeader("Accept-Language");
        if (temp == null) {
            temp = "";
        }
        logs.setAcceptLanguage(temp);
        temp = req.getRequestedSessionId();
        if (temp == null) {
            temp = "";
        }
        logs.setSessionId(temp);
        temp = resp.getContentType();
        if (temp == null) {
            temp = "";
        }
        logs.setContentType(temp);
        logs.setConsumeTime(-1);
        logs.setSource(source);
        logsService.createLog(logs);
    }

    @RequestMapping(value = "/401", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error401(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        createLogs(req, resp, "401");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.FORBIDDEN.value(), "projectY.ErrorController.error401", e, req.getServletPath()), HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/403", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error403(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        createLogs(req, resp, "403");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.FORBIDDEN.value(), "projectY.ErrorController.error403", e, req.getServletPath()), HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error404(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        createLogs(req, resp, "404");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "projectY.ErrorController.error404", e, req.getServletPath()), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/500", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error500(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        createLogs(req, resp, "500");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "projectY.ErrorController.error500", e, req.getServletPath()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
