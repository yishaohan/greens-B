package com.ysh.projectY.filter;

import com.ysh.projectY.authentication.MobilePhoneAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobilePhoneAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public MobilePhoneAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/doMobileLogin", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not support: " + request.getMethod());
        }
//        String mobilePhone = request.getParameter("mobilePhone");
//        if (mobilePhone == null) {
//            mobilePhone = "";
//        }
        String mobilePhone = request.getParameter("mobilePhone");
        if (mobilePhone == null || "".equals(mobilePhone)) {
//            authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.mobile-phone.not-blank"));
            throw new AuthenticationServiceException("project-y.valid.user.mobile-phone.not-blank");
        }
        if (!(mobilePhone.length() >= 10 && mobilePhone.length() <= 11)) {
            throw new AuthenticationServiceException("project-y.valid.user.mobile-phone.length");
        }
        try {
            Long.valueOf(mobilePhone);
        } catch (NumberFormatException e) {
            throw new AuthenticationServiceException("project-y.valid.user.mobile-phone.digits");
        }
        String smsCaptcha = request.getParameter("smsCaptcha");
        if (smsCaptcha == null || "".equals(smsCaptcha)) {
            throw new AuthenticationServiceException("project-y.valid.user.sms-captcha.not-blank");
        }
        if (smsCaptcha.length() != 6) {
            throw new AuthenticationServiceException("project-y.valid.user.sms-captcha.length");
        }
        try {
            Long.valueOf(smsCaptcha);
        } catch (NumberFormatException e) {
            throw new AuthenticationServiceException("project-y.valid.user.sms-captcha.digits");
        }
        mobilePhone = mobilePhone.trim();
        MobilePhoneAuthenticationToken authRequest = new MobilePhoneAuthenticationToken(mobilePhone, smsCaptcha);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, MobilePhoneAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
