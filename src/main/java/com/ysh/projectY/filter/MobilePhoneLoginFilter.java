//package com.ysh.projectY.filter;
//
//import com.ysh.projectY.handler.AuthenticationFailureHandler;
//import com.ysh.projectY.service.SmsCaptchaService;
//import com.ysh.projectY.utils.MethodResponse;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//// @Component(开启注解后,会导致在普通请求链中添加此过滤器)
//// 由MobilePhoneAuthenticationConfig类中手动添加到SpringSecurity请求链中
//public class MobilePhoneLoginFilter extends OncePerRequestFilter {
//
//    final AuthenticationFailureHandler authenticationFailureHandler;
//    final SmsCaptchaService smsCaptchaService;
//
//    public MobilePhoneLoginFilter(AuthenticationFailureHandler authenticationFailureHandler, SmsCaptchaService smsCaptchaService) {
//        this.authenticationFailureHandler = authenticationFailureHandler;
//        this.smsCaptchaService = smsCaptchaService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (("/api/v1/doMobileLogin").equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
//            String mobilePhone = request.getParameter("mobilePhone");
//            if (mobilePhone == null || "".equals(mobilePhone)) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.mobile-phone.not-blank"));
//                return;
//            }
//            if (!(mobilePhone.length() >= 10 && mobilePhone.length() <= 11)) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.mobile-phone.length"));
//                return;
//            }
//            try {
//                Long.valueOf(mobilePhone);
//            } catch (NumberFormatException e) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.mobile-phone.digits"));
//                return;
//            }
//            String smsCaptcha = request.getParameter("smsCaptcha");
//            if (smsCaptcha == null || "".equals(smsCaptcha)) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.sms-captcha.not-blank"));
//                return;
//            }
//            if (smsCaptcha.length() != 6) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.sms-captcha.length"));
//                return;
//            }
//            try {
//                Long.valueOf(smsCaptcha);
//            } catch (NumberFormatException e) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("project-y.valid.user.sms-captcha.digits"));
//                return;
//            }
//            MethodResponse methodResponse = smsCaptchaService.verifySmsCaptcha(mobilePhone, smsCaptcha);
//            if (!methodResponse.isSuccess()) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException(methodResponse.getI18nMessageKey()));
//                return;
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//
//}
