package com.ysh.projectY.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysh.projectY.entity.LoginLogs;
import com.ysh.projectY.entity.User;
import com.ysh.projectY.service.LoginLogsService;
import com.ysh.projectY.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    final LoginLogsService loginLogsService;

    public AuthenticationSuccessHandler(LoginLogsService loginLogsService) {
        this.loginLogsService = loginLogsService;
    }

    private void recordLoginLog(HttpServletRequest req) {
        LoginLogs loginLogs = new LoginLogs();
        String requestURI = req.getRequestURI();
        if (requestURI.contains("doLogin")) {
            loginLogs.setUsername(req.getParameter("username"));
            loginLogs.setPassword(req.getParameter("password"));
            loginLogs.setRemarks("Username Login");
        } else if (requestURI.contains("doMobileLogin")) {
            loginLogs.setMobilePhone(req.getParameter("mobilePhone"));
            loginLogs.setPassword(req.getParameter("smsCaptcha"));
            loginLogs.setRemarks("MobilePhone Login");
        } else {
            loginLogs.setUsername("Unknown Error!");
            loginLogs.setMobilePhone("Unknown Error!");
        }
        loginLogs.setLoginURL(requestURI);
        final HttpSession session = req.getSession(true);
        if (session != null) {
            loginLogs.setSessionID(session.getId());
        }
        loginLogs.setClientIP(req.getRemoteAddr());
        loginLogs.setStatus("SUCCESS");
        loginLogs.setLoginDateTime(new Timestamp(System.currentTimeMillis()));
        loginLogsService.saveAndFlush(loginLogs);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
        recordLoginLog(req);
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        User user = (User) authentication.getPrincipal();
        user.setPassword("");
        JsonResponse loginSuccess = JsonResponse.success(HttpStatus.OK.value(), "projectY.login.success", user);
        out.write(new ObjectMapper().writeValueAsString(loginSuccess));
        out.flush();
        out.close();
    }
}
