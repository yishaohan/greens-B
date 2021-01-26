package com.ysh.projectY.controller;

import com.ysh.projectY.entity.User;
import com.ysh.projectY.form.AddUser;
import com.ysh.projectY.form.UpdateUser;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.TimeZone;

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
     * @param addUser 新建用户表单
     * @return http 响应
     */
    @PostMapping("/register") //???????????????????????????????????????????????????????????????????
    public HttpEntity<?> addUser(@Valid @RequestBody AddUser addUser) {
        System.out.println("UserForm验证完成!");
        MethodResponse methodResponse = smsCaptchaService.verifySmsCaptcha(addUser.getMobilePhone(), addUser.getSmsCaptcha());
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        User user = new User();
        user.setUsername(addUser.getUsername());
        user.setMobilePhone(addUser.getMobilePhone());
        user.setPassword(addUser.getPassword());
        user.setNickname(addUser.getNickname());
        user.setAvatarURL(addUser.getAvatarURL());
        methodResponse = userDetailService.addUser(user);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
        }
    }

    @GetMapping("/admin/users")
    public HttpEntity<?> getUsers(@RequestParam(name = "nickname", defaultValue = "") String nickname,
                                  @RequestParam(name = "username", defaultValue = "") String username,
                                  @RequestParam(name = "mobilePhone", defaultValue = "") String mobilePhone,
                                  @RequestParam(name = "current", defaultValue = "1") int current,
                                  @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
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
        System.out.println("updateUser: " + updateUser);
        final Optional<User> optional = userDetailService.findById(updateUser.getId());
        User user = optional.get();
        user.setNickname(updateUser.getNickname());
        user.setUsername(updateUser.getUsername());
        user.setMobilePhone(updateUser.getMobilePhone());
        String newPassword = updateUser.getPassword();
        newPassword = "770519";
        if (newPassword != null && !"".equals(newPassword)) {
            user.setPassword(new BCryptPasswordEncoder(12).encode(updateUser.getPassword()));
        }
        user.setAvatarURL(updateUser.getAvatarURL());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            user.setCreateDateTime(new Timestamp(df.parse(updateUser.getCreateDateTime()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEnabled(updateUser.isEnabled());
        user.setLocked(updateUser.isLocked());
        final MethodResponse methodResponse = userDetailService.updateUser(user);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
        }
    }
}
