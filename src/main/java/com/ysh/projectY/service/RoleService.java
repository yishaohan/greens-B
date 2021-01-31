package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Optional<Role> findByID(int Id) {
        return roleDao.findById(Id);
    }
}
