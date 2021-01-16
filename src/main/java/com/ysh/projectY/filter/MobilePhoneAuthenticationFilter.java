package com.ysh.projectY.filter;

import com.ysh.projectY.authentication.MobilePhoneAuthenticationToken;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MobilePhoneAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public MobilePhoneAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/doMobileLogin", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not support: " + request.getMethod());
        }
        String mobilePhone = request.getParameter("mobilePhone");
        if (mobilePhone == null) {
            mobilePhone = "";
        }
        mobilePhone = mobilePhone.trim();
        MobilePhoneAuthenticationToken authRequest = new MobilePhoneAuthenticationToken(mobilePhone);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, MobilePhoneAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
