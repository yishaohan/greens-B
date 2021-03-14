package com.ysh.projectY.interceptor;

import com.ysh.projectY.entity.Logs;
import com.ysh.projectY.service.LogsService;
import org.springframework.core.NamedThreadLocal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class Request extends HandlerInterceptorAdapter {
    private final NamedThreadLocal<Long> time = new NamedThreadLocal<>("time");

    final LogsService logsService;

    public Request(LogsService logsService) {
        this.logsService = logsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        time.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        try {
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
            logs.setConsumeTime(System.currentTimeMillis() - time.get());
            logs.setSource("Interceptor");
            logsService.createLog(logs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
