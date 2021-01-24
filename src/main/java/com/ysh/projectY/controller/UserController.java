package com.ysh.projectY.controller;

import com.ysh.projectY.entity.User;
import com.ysh.projectY.entity.UserRegisterForm;
import com.ysh.projectY.service.SmsCaptchaService;
import com.ysh.projectY.service.UserDetailService;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @PostMapping("/register") //???????????????????????????????????????????????????????????????????
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

    @GetMapping("/admin/users")
    public HttpEntity<?> getUsers(@RequestParam(name = "current", defaultValue = "0") int current, @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        Pageable pageable = PageRequest.of(current-1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        final Page<User> users = userDetailService.getUsers(pageable);
//        final List<User> content = users.getContent();
//        System.out.println("+++++++++++++++++++++++++++++++++++++");
//        for (User user : content) {
//            System.out.println(user);
//        }
//        System.out.println("+++++++++++++++++++++++++++++++++++++");
//        final long totalElements = users.getTotalElements();
        try {
            return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.UserController.getUsers.success", users, users.toString()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
