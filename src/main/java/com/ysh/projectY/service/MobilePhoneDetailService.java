package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.dao.UserDao;
import com.ysh.projectY.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MobilePhoneDetailService implements UserDetailsService {

    final UserDao userDao;
    final RoleDao roleDao;

    public MobilePhoneDetailService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String mobilePhone) throws UsernameNotFoundException {
        User user = userDao.loadUserByMobilePhone(mobilePhone);
        if (user == null) {
            throw new UsernameNotFoundException("mobile phone not found!!!");
        }
//        user.setRoles(roleDao.getUserRolesByUserId(user.getId()));
        return user;
    }

}
