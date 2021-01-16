package com.ysh.projectY.provider;

import com.ysh.projectY.service.SmsCaptchaService;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

// UsernamePasswordAuthenticationToken认证方式下处理图片验证码 ??????????????/
// 验证逻辑太靠后, 替换成Filter验证方式  ?????????????????/
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

//    @Autowired
//    SmsCaptchaService smsCaptchaService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
