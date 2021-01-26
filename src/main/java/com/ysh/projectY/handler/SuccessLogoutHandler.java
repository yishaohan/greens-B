package com.ysh.projectY.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysh.projectY.entity.LoginLogs;
import com.ysh.projectY.entity.User;
import com.ysh.projectY.service.LoginLogsService;
import com.ysh.projectY.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@Component
public class SuccessLogoutHandler implements LogoutSuccessHandler {

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
        String msg = "projectY.logout.failure.NotLoggedIn";
        String detail = "用户退出失败 - 尚未登录!";
        User user = null;
        if (auth != null) {
            msg = "projectY.logout.success";
            detail = "用户退出成功!";
            user = (User) auth.getPrincipal();
        }
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT");
        resp.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "1800");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        JsonResponse loginFailure = JsonResponse.success(HttpStatus.OK.value(), msg, user, detail);
        out.write(new ObjectMapper().writeValueAsString(loginFailure));
        out.flush();
        out.close();

    }
}
