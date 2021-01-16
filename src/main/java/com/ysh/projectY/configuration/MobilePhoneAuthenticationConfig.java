package com.ysh.projectY.configuration;

import com.ysh.projectY.authentication.MobilePhoneAuthenticationProvider;
import com.ysh.projectY.filter.MobilePhoneAuthenticationFilter;
import com.ysh.projectY.handler.AuthenticationFailureHandler;
import com.ysh.projectY.handler.AuthenticationSuccessHandler;
import com.ysh.projectY.service.MobilePhoneDetailService;
import com.ysh.projectY.service.SmsCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MobilePhoneAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MobilePhoneDetailService mobilePhoneDetailService;

    @Autowired
    SmsCaptchaService smsCaptchaService;

    @Override
    public void configure(HttpSecurity http) {
        MobilePhoneAuthenticationFilter mobilePhoneAuthenticationFilter = new MobilePhoneAuthenticationFilter();
        final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        mobilePhoneAuthenticationFilter.setAuthenticationManager(authenticationManager);
        mobilePhoneAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        mobilePhoneAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        final SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
        mobilePhoneAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
//        http.addFilterBefore(new MobilePhoneLoginFilter(authenticationFailureHandler, smsCaptchaService), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(mobilePhoneAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        MobilePhoneAuthenticationProvider mobilePhoneAuthenticationProvider = new MobilePhoneAuthenticationProvider();
        mobilePhoneAuthenticationProvider.setUserDetailsService(mobilePhoneDetailService);
        http.authenticationProvider(mobilePhoneAuthenticationProvider);
        final RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
        mobilePhoneAuthenticationFilter.setRememberMeServices(rememberMeServices);
    }
}
