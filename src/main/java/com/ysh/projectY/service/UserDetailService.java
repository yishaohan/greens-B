package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.dao.UserDao;
import com.ysh.projectY.entity.Role;
import com.ysh.projectY.entity.User;
import com.ysh.projectY.form.CreateUser;
import com.ysh.projectY.form.RegisterUser;
import com.ysh.projectY.form.UpdateUser;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleService roleService;

//    @Autowired
//    RoleDao roleDao;

//    private void getUserRoles(User user) {
//        user.setRoles(roleDao.getUserRolesByUserId(user.getId()));
//    }

    private void erasePassword(User user) {
        user.setPassword("");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername(String username=" + username + ")");
        User user = userDao.loadUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("account not found!!!");
        }
//        erasePassword(user);  不能在此清除密码
//        getUserRoles(user);
        return user;
    }

    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Optional<User> findByMobilePhone(String mobilePhone) {
        return userDao.findByMobilePhone(mobilePhone);
    }

    public void saveAndFlush(User user) {
        userDao.saveAndFlush(user);
    }

    public Page<User> getUsers(String nickname, String username, String mobilePhone, Pageable pageable) {
        final Page<User> page = userDao.findAllByNicknameContainingAndUsernameContainingAndMobilePhoneContaining(nickname, username, mobilePhone, pageable);
        final List<User> content = page.getContent();
        return page;
    }

    public MethodResponse registerUser(RegisterUser registerUser) {
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setMobilePhone(registerUser.getMobilePhone());
        user.setNickname(registerUser.getNickname());
        user.setAvatarURL(registerUser.getAvatarURL());
        // 加密密码
        user.setPassword(new BCryptPasswordEncoder(12).encode(registerUser.getPassword()));
        // 设置创建时间
        user.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
        // 设置用户是否启用
        user.setEnabled(true);
        // 设置用户是否锁定
        user.setLocked(false);
        try {
            userDao.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.UserService.registerUser.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.UserService.registerUser.success");
    }

    public MethodResponse updateUser(UpdateUser updateUser) {
        System.out.println("updateUser: " + updateUser);
        final Optional<User> optional = userDao.findById(updateUser.getId());
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
        try {
            userDao.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.UserService.updateUser.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.UserService.updateUser.success");
    }

    public MethodResponse deleteUser(int id) {
        final Optional<User> optional = userDao.findById(id);
        if (optional.isEmpty()) {
            return MethodResponse.failure("projectY.UserService.deleteUser.failure.id-not-exist");
        }
        userDao.delete(optional.get());
        return MethodResponse.success("projectY.UserService.deleteUser.success");
    }

    public MethodResponse deleteUsers(List<Integer> ids) {
        StringBuilder detail = new StringBuilder();
        for (Integer id : ids) {
            final Optional<User> optional = userDao.findById(id);
            if (optional.isEmpty()) {
                detail.append(id).append(", ");
                continue;
            }
            userDao.delete(optional.get());
        }
        if (!"".equals(detail.toString())) {
            detail = new StringBuilder("[ " + detail.substring(0, detail.length() - 2) + " ] User not Exist");
            return MethodResponse.failure("projectY.UserService.deleteUsers.failure.ids-not-exist", detail.toString(), null);
        }
        return MethodResponse.success("projectY.UserService.deleteUsers.success");
    }

    public MethodResponse createUser(CreateUser createUser) {
        User user = new User();
        user.setUsername(createUser.getUsername());
        user.setMobilePhone(createUser.getMobilePhone());
        user.setNickname(createUser.getNickname());
        user.setAvatarURL(createUser.getAvatarURL());
        // 加密密码
        user.setPassword(new BCryptPasswordEncoder(12).encode(createUser.getPassword()));
        // 设置创建时间
        user.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
        // 设置用户是否启用
        user.setEnabled(true);
        // 设置用户是否锁定
        user.setLocked(false);
        // 设置用户角色
        List<Role> roles = new ArrayList<>();
        final Optional<Role> optional = roleService.findByID(2);
        if (optional.isPresent()) {
            roles.add(optional.get());
        }
        user.setRoles(roles);
        try {
            userDao.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.UserService.createUser.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.UserService.createUser.success");
    }
}
