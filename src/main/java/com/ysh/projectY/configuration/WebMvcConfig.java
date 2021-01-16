package com.ysh.projectY.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${projectY.api-base-path}")
    private String apiBasePath;

    @Value("${projectY.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(apiBasePath + "/**")
                .allowedHeaders("*")
                .allowedMethods("*")
//                .exposedHeaders("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true)  // 允许前端携带cookie
                .maxAge(1800);
    }
}
