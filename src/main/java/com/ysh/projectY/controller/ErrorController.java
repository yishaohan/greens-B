package com.ysh.projectY.controller;

import com.ysh.projectY.utils.JsonResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "${projectY.api-base-path}" + "/error")
public class ErrorController {

    @RequestMapping(value = "/403", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error403(HttpServletRequest req) {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.FORBIDDEN.value(), "projectY.ErrorController.error403", null, req.getServletPath()), HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error404(HttpServletRequest req) {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "projectY.ErrorController.error404", null, req.getServletPath()), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/500", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpEntity<?> error500(HttpServletRequest req) {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "projectY.ErrorController.error500", null, req.getServletPath()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
