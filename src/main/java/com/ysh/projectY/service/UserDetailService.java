package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.dao.UserDao;
import com.ysh.projectY.entity.User;
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
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

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
        final Optional<User> optional = userDao.findById(id);
        if (optional.isPresent()) {
//            getUserRoles(optional.get());
        }
        return optional;
    }

    public Optional<User> findByUsername(String username) {
        final Optional<User> optional = userDao.findByUsername(username);
        if (optional.isPresent()) {
//            getUserRoles(optional.get());
        }
        return optional;
    }

    public Optional<User> findByMobilePhone(String mobilePhone) {
        final Optional<User> optional = userDao.findByMobilePhone(mobilePhone);
        if (optional.isPresent()) {
//            getUserRoles(optional.get());
        }
        return optional;
    }

    public void saveAndFlush(User user) {
        userDao.saveAndFlush(user);
    }

    public Page<User> getUsers(String nickname, String username, String mobilePhone, Pageable pageable) {
        final Page<User> page = userDao.findAllByNicknameContainingAndUsernameContainingAndMobilePhoneContaining(nickname, username, mobilePhone, pageable);
        final List<User> content = page.getContent();
        for (User user : content) {
//            getUserRoles(user);
        }
        return page;
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

    public MethodResponse updateUser(User user) {
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
}
