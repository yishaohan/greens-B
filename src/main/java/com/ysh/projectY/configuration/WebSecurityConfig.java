package com.ysh.projectY.configuration;

import com.ysh.projectY.filter.UsernamePasswordLoginFilter;
import com.ysh.projectY.handler.AuthenticationFailureHandler;
import com.ysh.projectY.handler.AuthenticationSuccessHandler;
import com.ysh.projectY.handler.SuccessLogoutHandler;
import com.ysh.projectY.provider.CustomAuthenticationProvider;
import com.ysh.projectY.service.RememberMeTokenRepositoryImpl;
import com.ysh.projectY.service.UserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.port}")
    private int httpsPort;
    @Value("${http.port}")
    private int httpPort;

    @Value("${projectY.api-base-path}")
    private String apiBasePath;

    final AuthenticationSuccessHandler authenticationSuccessHandler;
    final AuthenticationFailureHandler authenticationFailureHandler;
    final RememberMeTokenRepositoryImpl rememberMeTokenRepositoryImpl;
    final MobilePhoneAuthenticationConfig mobilePhoneAuthenticationConfig;
    final SuccessLogoutHandler successLogoutHandler;

    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, RememberMeTokenRepositoryImpl rememberMeTokenRepositoryImpl, MobilePhoneAuthenticationConfig mobilePhoneAuthenticationConfig, SuccessLogoutHandler successLogoutHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.rememberMeTokenRepositoryImpl = rememberMeTokenRepositoryImpl;
        this.mobilePhoneAuthenticationConfig = mobilePhoneAuthenticationConfig;
        this.successLogoutHandler = successLogoutHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.portMapper().http(httpPort).mapsTo(httpsPort);
        http.requiresChannel(channel -> channel.anyRequest().requiresSecure());

        http.addFilterBefore(new UsernamePasswordLoginFilter(authenticationFailureHandler), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers(apiBasePath + "/users/**")
                .hasRole("admin")
                .antMatchers(apiBasePath + "/submit/**")
                .hasRole("user")
                .anyRequest()
                .permitAll();

        http.formLogin()
                .loginPage(apiBasePath + "/login")
                .loginProcessingUrl(apiBasePath + "/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .rememberMe()
                .rememberMeCookieName("autoLogin")
                .rememberMeParameter("autoLogin")
                .key("projectY")
                .tokenRepository(rememberMeTokenRepositoryImpl)
                .tokenValiditySeconds(60 * 60 * 24 * 30)
                .useSecureCookie(true);

        //前台还没有实现Logout
        http.logout()
                .logoutUrl(apiBasePath + "/logout")
                .logoutSuccessHandler(successLogoutHandler)
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("PHPSESSID");  // ??????????????

        http.csrf().disable();

        http.sessionManagement()
                .invalidSessionUrl(apiBasePath + "/invalidSession")
                .maximumSessions(1)
                .expiredUrl(apiBasePath + "/expiredSession")
                .maxSessionsPreventsLogin(false);

        http.apply(mobilePhoneAuthenticationConfig);
    }

    // 自定义customAuthenticationProvider, 验证码认证逻辑太靠后, 用Filter代替 ????? 不对
    CustomAuthenticationProvider customAuthenticationProvider() {
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        customAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        customAuthenticationProvider.setUserDetailsService(userDetailsService());
        return customAuthenticationProvider;
    }

    @Bean
    @Override
    protected UserDetailService userDetailsService() {
        return new UserDetailService();
    }

    // 添加自定义的AuthenticationProvider
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(customAuthenticationProvider()));
    }

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

}
