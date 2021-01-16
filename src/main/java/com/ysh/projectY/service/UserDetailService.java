package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.dao.UserDao;
import com.ysh.projectY.entity.User;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername(String username=" + username + ")");
        User user = userDao.loadUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("account not found!!!");
        }
        user.setRoles(roleDao.getUserRolesByUserId(user.getId()));
        return user;
    }

    public MethodResponse addUser(User user) {
        // 加密密码
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
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
            return MethodResponse.failure("projectY.UserService.addUser.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.UserService.addUser.success");
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
}
