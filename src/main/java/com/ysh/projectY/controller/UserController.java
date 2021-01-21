package com.ysh.projectY.controller;

import com.ysh.projectY.entity.User;
import com.ysh.projectY.entity.UserRegisterForm;
import com.ysh.projectY.service.SmsCaptchaService;
import com.ysh.projectY.service.UserDetailService;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * rest 风格 api
 * <p>
 * GET     /books        所有书单
 * GET     /books/{id}   获取一条书单
 * POST    /books        新建一条书单
 * PUT     /books/{id}   更新一条书单，提供全部信息
 * PATCH   /books/{id}   更新一条书单，提供部分信息
 * DELETE  /books/{id}   删除一条书单
 * DELETE  /books        删除所有书单
 *
 * @author YCZ
 * @version 1.0
 * @date 2021-01-05 21:59
 */

@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class UserController {

    final UserDetailService userDetailService;
    final SmsCaptchaService smsCaptchaService;

    public UserController(UserDetailService userDetailService, SmsCaptchaService smsCaptchaService) {
        this.userDetailService = userDetailService;
        this.smsCaptchaService = smsCaptchaService;
    }

    /**
     * 新建用户
     * POST    /users        新建用户
     *
     * @param userRegisterForm 新建用户表单
     * @return http 响应
     */
    @PostMapping("/register")
    public HttpEntity<?> addUser(@Valid @RequestBody UserRegisterForm userRegisterForm) {
        System.out.println("UserForm验证完成!");
        MethodResponse methodResponse = smsCaptchaService.verifySmsCaptcha(userRegisterForm.getMobilePhone(), userRegisterForm.getSmsCaptcha());
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        User user = new User();
        user.setUsername(userRegisterForm.getUsername());
        user.setMobilePhone(userRegisterForm.getMobilePhone());
        user.setPassword(userRegisterForm.getPassword());
        user.setNickname(userRegisterForm.getNickname());
        user.setAvatarURL(userRegisterForm.getAvatarURL());
        methodResponse = userDetailService.addUser(user);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
        }
    }

}
