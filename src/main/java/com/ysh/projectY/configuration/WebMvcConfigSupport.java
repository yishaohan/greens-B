package com.ysh.projectY.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

//@Configuration
public class WebMvcConfigSupport extends WebMvcConfigurationSupport {
//
//    @Value("${projectY.api-base-path}")
//    private String apiBasePath;
//
//    以下方式会导致RememberMe登录时,得不到请求的URL, 换成WebMvcConfig的addResourceHandlers()方法, 绕过此问题
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        final String property = System.getProperty("catalina.home");
//        String realPath = property + File.separator + "work" + File.separator + "resource" + File.separator + "avatars" + File.separator;
//        registry.addResourceHandler(apiBasePath + "/user/avatars/**").addResourceLocations("file:" + realPath);
//        super.addResourceHandlers(registry);
//    }
}
