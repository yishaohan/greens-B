package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.entity.Role;
import com.ysh.projectY.entity.User;
import com.ysh.projectY.form.CreateRole;
import com.ysh.projectY.form.UpdateRole;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Page<Role> getRoles(String roleName, String roleDescript, Pageable pageable) {
        final Page<Role> page = roleDao.findAllByRoleNameContainingAndRoleDescriptContaining(roleName, roleDescript, pageable);
        return page;
    }

    public MethodResponse updateRole(UpdateRole updateRole) {
        final Optional<Role> optional = roleDao.findById(updateRole.getId());
        Role role = optional.get();
        role.setRoleName(updateRole.getRoleName());
        String newRoleDescript = updateRole.getRoleDescript();
        if (newRoleDescript != null && !"".equals(newRoleDescript)) {
            role.setRoleDescript(newRoleDescript);
        }
        Boolean newEnabled = updateRole.getEnabled();
        if (newEnabled != null) {
            role.setEnabled(newEnabled);
        }
        try {
            roleDao.saveAndFlush(role);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.RoleService.updateRole.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.RoleService.updateRole.success");
    }

    public MethodResponse deleteRole(int id) {
        final Optional<Role> optional = roleDao.findById(id);
        if (optional.isEmpty()) {
            return MethodResponse.failure("projectY.RoleService.deleteRole.failure.id-not-exist");
        }
        roleDao.delete(optional.get());
        return MethodResponse.success("projectY.RoleService.deleteRole.success");
    }

    public MethodResponse deleteRoles(List<Integer> ids) {
        StringBuilder detail = new StringBuilder();
        for (Integer id : ids) {
            final Optional<Role> optional = roleDao.findById(id);
            if (optional.isEmpty()) {
                detail.append(id).append(", ");
                continue;
            }
            roleDao.delete(optional.get());
        }
        if (!"".equals(detail.toString())) {
            detail = new StringBuilder("[ " + detail.substring(0, detail.length() - 2) + " ] User not Exist");
            return MethodResponse.failure("projectY.RoleService.deleteRoles.failure.ids-not-exist", detail.toString(), null);
        }
        return MethodResponse.success("projectY.RoleService.deleteRoles.success");
    }

    public MethodResponse createRole(CreateRole createRole) {
        Role role = new Role();
        role.setRoleName(createRole.getRoleName());
        role.setRoleDescript(createRole.getRoleDescript());
        role.setEnabled(true);
        try {
            roleDao.saveAndFlush(role);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.RoleService.createRole.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.RoleService.createRole.success");
    }

    public Optional<Role> findByRoleName(String roleName) {
        return roleDao.findByRoleName(roleName);
    }
}
