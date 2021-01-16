package com.ysh.projectY.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysh.projectY.entity.LoginLogs;
import com.ysh.projectY.entity.User;
import com.ysh.projectY.service.LoginLogsService;
import com.ysh.projectY.service.UserDetailService;
import com.ysh.projectY.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Optional;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    final LoginLogsService loginLogsService;
    final UserDetailService userDetailService;

    public AuthenticationFailureHandler(LoginLogsService loginLogsService, UserDetailService userDetailService) {
        this.loginLogsService = loginLogsService;
        this.userDetailService = userDetailService;
    }

    private void record(HttpServletRequest req, String remarks) {
        LoginLogs loginLogs = new LoginLogs();
        String username = null;
        String mobilePhone = null;
        String requestURI = req.getRequestURI();
        if (requestURI.contains("doLogin")) {
            username = req.getParameter("username");
            loginLogs.setUsername(username);
            loginLogs.setPassword(req.getParameter("password"));
        } else if (requestURI.contains("doMobileLogin")) {
            mobilePhone = req.getParameter("mobilePhone");
            loginLogs.setMobilePhone(mobilePhone);
            loginLogs.setPassword(req.getParameter("smsCaptcha"));
        } else {
            loginLogs.setUsername("Unknown Error!");
            loginLogs.setMobilePhone("Unknown Error!");
        }
        loginLogs.setLoginURL(requestURI);
        loginLogs.setSessionID(req.getSession().getId());
        loginLogs.setClientIP(req.getRemoteAddr());
        loginLogs.setStatus("FAILURE");
        loginLogs.setLoginDateTime(new Timestamp(System.currentTimeMillis()));
        loginLogs.setRemarks(remarks);
        loginLogsService.saveAndFlush(loginLogs);
        if (username != null) {
            userLockedCheckByUsername(username);
        }
        if (mobilePhone != null) {
            userLockedCheckByMobilePhone(mobilePhone);
        }
    }

    private void userLockedCheckByUsername(String username) {
        int count = loginLogsService.findLastFailureCountByUsername(username);
        System.out.println("count: " + count);
        if (count >= 3) {
            final Optional<User> optional = userDetailService.findByUsername(username);
            if (optional.isPresent()) {
                User user = optional.get();
                user.setLocked(true);
                userDetailService.saveAndFlush(user);
            }
        }
    }

    private void userLockedCheckByMobilePhone(String mobilePhone) {
        int count = loginLogsService.findLastFailureCountByMobilePhone(mobilePhone);
        System.out.println("count: " + count);
        if (count >= 3) {
            final Optional<User> optional = userDetailService.findByMobilePhone(mobilePhone);
            if (optional.isPresent()) {
                User user = optional.get();
                user.setLocked(true);
                userDetailService.saveAndFlush(user);
            }
        }
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException {
        String msg;
        String detail;
        if (e instanceof LockedException) {
            msg = "projectY.login.failure.LockedException";
            detail = "Account Locked!";
        } else if (e instanceof BadCredentialsException) {
            msg = "projectY.login.failure.BadCredentialsException";
            detail = "Bad Credentials!";
        } else if (e instanceof DisabledException) {
            msg = "projectY.login.failure.DisabledException";
            detail = "Account Disabled!";
        } else if (e instanceof AccountExpiredException) {
            msg = "projectY.login.failure.AccountExpiredException";
            detail = "Account Expired!";
        } else if (e instanceof CredentialsExpiredException) {
            msg = "projectY.login.failure.CredentialsExpiredException";
            detail = "Credentials Expired!";
        } else if (e instanceof AuthenticationServiceException) {
//            msg = "projectY.login.failure.AuthenticationServiceException";
            msg = e.getMessage();
            detail = "Authentication Service Exception: " + e.toString();
        } else if (e instanceof UsernameNotFoundException) {
            msg = "projectY.login.failure.BadCredentialsException";
            detail = "Authentication Service Exception: " + e.toString();
        } else {
            msg = "projectY.login.failure.Exception";
            detail = "Exception!";
        }
        record(req, detail);
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        JsonResponse loginFailure = JsonResponse.success(HttpStatus.UNAUTHORIZED.value(), msg, e.toString(), detail);
        out.write(new ObjectMapper().writeValueAsString(loginFailure));
        out.flush();
        out.close();

    }
}
