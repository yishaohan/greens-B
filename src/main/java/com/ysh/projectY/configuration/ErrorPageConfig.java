package com.ysh.projectY.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Value("${projectY.api-base-path}")
    private String apiBasePath;

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(
                new ErrorPage(HttpStatus.UNAUTHORIZED, apiBasePath + "/error/401"),
                new ErrorPage(HttpStatus.FORBIDDEN, apiBasePath + "/error/403"),
                new ErrorPage(HttpStatus.NOT_FOUND, apiBasePath + "/error/404"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, apiBasePath + "/error/500"));
    }
}
