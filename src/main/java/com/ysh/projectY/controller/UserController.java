package com.ysh.projectY.controller;

import com.ysh.projectY.entity.User;
import com.ysh.projectY.form.*;
import com.ysh.projectY.service.SmsCaptchaService;
import com.ysh.projectY.service.UserDetailService;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
     * @param registerUser 新建用户表单
     * @return http 响应
     */
    @PostMapping("/register") //???????????????????????????????????????????????????????????????????
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterUser registerUser) {
        MethodResponse methodResponse = smsCaptchaService.verifySmsCaptcha(registerUser.getMobilePhone(), registerUser.getSmsCaptcha());
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        methodResponse = userDetailService.registerUser(registerUser);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @GetMapping("/admin/users")
    public HttpEntity<?> getUsers(@RequestParam(name = "nickname", defaultValue = "") String nickname,
                                  @RequestParam(name = "username", defaultValue = "") String username,
                                  @RequestParam(name = "mobilePhone", defaultValue = "") String mobilePhone,
                                  @RequestParam(name = "current", defaultValue = "1") int current,
                                  @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        final Page<User> users = userDetailService.getUsers(nickname, username, mobilePhone, pageable);
//        final List<User> content = users.getContent();
//        System.out.println("+++++++++++++++++++++++++++++++++++++");
//        for (User user : content) {
//            System.out.println(user);
//        }
//        System.out.println("+++++++++++++++++++++++++++++++++++++");
//        final long totalElements = users.getTotalElements();
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.UserController.getUsers.success", users, users.toString()), HttpStatus.OK);
    }

    @PutMapping("/admin/users")
    public HttpEntity<?> updateUser(@Valid @RequestBody UpdateUser updateUser) {
        final MethodResponse methodResponse = userDetailService.updateUser(updateUser);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/users/{id}")
    public HttpEntity<?> deleteUser(@PathVariable(name = "id") int id) {
        final MethodResponse methodResponse = userDetailService.deleteUser(id);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @DeleteMapping("/admin/users")
    public HttpEntity<?> deleteUsers(@Valid @RequestBody List<Integer> ids) {
        final MethodResponse methodResponse = userDetailService.deleteUsers(ids);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public HttpEntity<?> createUser(@Valid @RequestBody CreateUser createUser) {
        final MethodResponse methodResponse = userDetailService.createUser(createUser);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @PostMapping("/admin/users/avatar")
    public HttpEntity<?> createAvatar(@RequestPart("avatar") @Valid @NotNull @NotBlank MultipartFile uploadFile, HttpServletRequest req) {
        final MethodResponse methodResponse = userDetailService.createAvatar(uploadFile, req);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @PostMapping("/admin/users/roles")
    public HttpEntity<?> addUserRole(@Valid @RequestBody AddUserRole addUserRole) {
        final MethodResponse methodResponse = userDetailService.addUserRole(addUserRole);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/users/roles")
    public HttpEntity<?> deleteUserRole(@Valid @RequestBody DeleteUserRole deleteUserRole) {
        final MethodResponse methodResponse = userDetailService.deleteUserRole(deleteUserRole);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

}
