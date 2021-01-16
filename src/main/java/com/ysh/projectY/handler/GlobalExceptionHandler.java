package com.ysh.projectY.handler;

import com.ysh.projectY.utils.JsonResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方法异常处理器
     *
     * @param e 忽略参数异常
     * @return ResultObject
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public HttpEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        List<String> errorsKey = new ArrayList<>();
        errorsKey.add(e.getMessage());
        System.out.println(e);
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.HttpRequestMethodNotSupportedException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }


    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResultObject
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public HttpEntity<?> missingServletRequestParameter(MissingServletRequestParameterException e) {
        List<String> errorsKey = new ArrayList<>();
        errorsKey.add(e.getMessage());
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.MissingServletRequestParameterException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 媒体类型不支持异常处理器
     *
     * @param e 类型不匹配异常
     * @return resultObject
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public HttpEntity<?> httpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        List<String> errorsKey = new ArrayList<>();
        errorsKey.add(e.getMessage());
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.HttpMediaTypeNotSupportedException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResultObject
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HttpEntity<?> httpMessageNotReadable(HttpMessageNotReadableException e) {
//        return ResultObject.createByErrorMessage("参数体校验错误");
        List<String> errorsKey = new ArrayList<>();
        errorsKey.add(e.getMessage());
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.HttpMessageNotReadableException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * hibernate validator 数据绑定验证异常拦截
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public HttpEntity<?> bind(BindException e) {
        List<String> errorsKey = new ArrayList<>();
        List<ObjectError> errors = e.getAllErrors();
        for (ObjectError error : errors) {
            errorsKey.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.BindException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * hibernate validator 数据绑定验证异常拦截
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<?> methodArgumentNotValid(MethodArgumentNotValidException e) {
        List<String> errorsKey = new ArrayList<>();
        List<ObjectError> errors = e.getAllErrors();
        for (ObjectError error : errors) {
            errorsKey.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.MethodArgumentNotValidException", errorsKey, e.toString()), HttpStatus.OK);
    }


    /**
     * hibernate validator 数据绑定验证异常拦截
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public HttpEntity<?> constraintViolation(ConstraintViolationException e) {
        List<String> errorsKey = new ArrayList<>();
        final Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            errorsKey.add(constraintViolation.getMessage());
        }
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.ConstraintViolationException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 参数校验过程中发生的异常
     *
     * @param e 参数校验异常
     * @return resultObject
     */
    @ExceptionHandler(ValidationException.class)
    public HttpEntity<?> validation(ValidationException e) {
//        String message = e.getCause().getMessage();
        List<String> errorsKey = new ArrayList<>();
        errorsKey.add(e.getMessage());
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.BAD_REQUEST.value(), "projectY.GlobalExceptionHandler.ValidationException", errorsKey, e.toString()), HttpStatus.BAD_REQUEST);
    }

    //    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HttpEntity<?> exception(Exception e) {
        return new ResponseEntity<>(JsonResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "projectY.GlobalExceptionHandler.Exception", e, e.getClass() + ": " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
