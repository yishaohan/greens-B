package com.ysh.projectY.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${projectY.api-base-path}")
    private String apiBasePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String property = System.getProperty("catalina.home");
        String realPath = property + File.separator + "work" + File.separator + "resource" + File.separator + "avatars" + File.separator;
        registry.addResourceHandler(apiBasePath + "/user/avatars/**").addResourceLocations("file:" + realPath);
        realPath = property + File.separator + "work" + File.separator + "resource" + File.separator + "covid19" + File.separator;
        registry.addResourceHandler("/public/covid19/**").addResourceLocations("file:" + realPath);
    }

//    @Value("${projectY.api-base-path}")
//    private String apiBasePath;
//
//    @Value("${projectY.allowed-origins}")
//    private String allowedOrigins;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping(apiBasePath + "/**")
//                .allowedOrigins(allowedOrigins)
//                .allowCredentials(true)  // 允许前端携带cookie
//                .allowedHeaders("Authorization", "Content-Type")
//                .allowedMethods("OPTIONS", "GET", "POST", "PUT")
////                .exposedHeaders("content-type")
//                .maxAge(1800);
//    }
}
