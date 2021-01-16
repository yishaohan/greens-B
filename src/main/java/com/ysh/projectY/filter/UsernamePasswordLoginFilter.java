package com.ysh.projectY.filter;

import com.ysh.projectY.handler.AuthenticationFailureHandler;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class UsernamePasswordLoginFilter extends OncePerRequestFilter {

    final AuthenticationFailureHandler authenticationFailureHandler;

    public UsernamePasswordLoginFilter(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (("/api/v1/doLogin").equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
            String username = request.getParameter("username");
            if (username == null || "".equals(username)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.username.not-blank"));
                return;
            }
            if (username.startsWith("@") || !username.contains("@") || username.startsWith(".") || !username.contains(".")) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.username.email"));
                return;
            }
            String password = request.getParameter("password");
            if (password == null || "".equals(password)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.password.not-blank"));
                return;
            }
            if (!(password.length() >= 6 && password.length() <= 18)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.password.size"));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


}
