package com.ysh.projectY.service;

import com.ysh.projectY.dao.RoleDao;
import com.ysh.projectY.entity.Authorization;
import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.entity.Role;
import com.ysh.projectY.form.CreateRole;
import com.ysh.projectY.form.UpdateRole;
import com.ysh.projectY.form.UpdateRoleAuths;
import com.ysh.projectY.form.UpdateRoleMenus;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    final RoleDao roleDao;
    final AuthorizationService authService;
    final MenuService menuService;

    public RoleService(RoleDao roleDao, AuthorizationService authService, final MenuService menuService) {
        this.roleDao = roleDao;
        this.authService = authService;
        this.menuService = menuService;
    }

    public Optional<Role> findById(int Id) {
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
        if (roleDao.findUsedCountByRoleId(id) > 0) {
            return MethodResponse.failure("projectY.RoleService.deleteRole.failure.role-is-used");
        }
        roleDao.delete(optional.get());
        return MethodResponse.success("projectY.RoleService.deleteRole.success");
    }

    public MethodResponse deleteRoles(List<Integer> ids) {
        StringBuilder roleNotExist = new StringBuilder();
        StringBuilder roleIsUsed = new StringBuilder();
        for (Integer id : ids) {
            final Optional<Role> optional = roleDao.findById(id);
            if (optional.isEmpty()) {
                roleNotExist.append(id).append(", ");
                continue;
            }
            if (roleDao.findUsedCountByRoleId(id) > 0) {
                roleIsUsed.append(id).append(", ");
                continue;
            }
            roleDao.delete(optional.get());
        }
        if (!"".equals(roleIsUsed.toString())) {
            roleIsUsed = new StringBuilder("[ " + roleIsUsed.substring(0, roleIsUsed.length() - 2) + " ] Role is Used");
            return MethodResponse.failure("projectY.RoleService.deleteRoles.failure.ids-is-used", roleIsUsed.toString(), null);
        }
        if (!"".equals(roleNotExist.toString())) {
            roleNotExist = new StringBuilder("[ " + roleNotExist.substring(0, roleNotExist.length() - 2) + " ] Role not Exist; ");
            return MethodResponse.failure("projectY.RoleService.deleteRoles.failure.ids-not-exist", roleNotExist.toString(), null);
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

    public MethodResponse updateRoleAuths(UpdateRoleAuths updateRoleAuths) {
        StringBuilder addAuthsDetail = new StringBuilder();
        StringBuilder removeAuthsDetail = new StringBuilder();
        final List<Integer> addAuthIds = updateRoleAuths.getAddAuthIds();
        final List<Integer> removeAuthIds = updateRoleAuths.getRemoveAuthIds();
        final Optional<Role> o_role = roleDao.findById(updateRoleAuths.getRoleId());
        if (o_role.isEmpty()) {
            return MethodResponse.failure("projectY.RoleService.updateRoleAuths.failure.roleId-not-exist", null, null);
        }
        Role role = o_role.get();
        final List<Authorization> auths = role.getAuths();
        for (Integer addAuthId : addAuthIds) {
            final Optional<Authorization> o_auth = authService.findById(addAuthId);
            if (o_auth.isEmpty()) {
                addAuthsDetail.append(addAuthId).append(", ");
                continue;
            }
            Authorization auth = o_auth.get();
            if (!auths.contains(auth)) {
                auths.add(auth);
            }
        }
        for (Integer removeAuthId : removeAuthIds) {
            final Optional<Authorization> o_auth = authService.findById(removeAuthId);
            if (o_auth.isEmpty()) {
                removeAuthsDetail.append(removeAuthId).append(", ");
                continue;
            }
            Authorization auth = o_auth.get();
            if (auths.contains(auth)) {
                auths.remove(auth);
            }
        }
        try {
            roleDao.saveAndFlush(role);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.RoleService.updateRoleAuths.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.RoleService.updateRoleAuths.success");
    }

    public MethodResponse updateRoleMenus(UpdateRoleMenus updateRoleMenus) {
        StringBuilder addMenusDetail = new StringBuilder();
        StringBuilder removeMenusDetail = new StringBuilder();
        final List<Integer> addMenuIds = updateRoleMenus.getAddMenuIds();
        final List<Integer> removeMenuIds = updateRoleMenus.getRemoveMenuIds();
        final Optional<Role> o_role = roleDao.findById(updateRoleMenus.getRoleId());
        if (o_role.isEmpty()) {
            return MethodResponse.failure("projectY.RoleService.updateRoleMenus.failure.roleId-not-exist", null, null);
        }
        Role role = o_role.get();
        final List<Menu> menus = role.getMenus();
        for (Integer addMenuId : addMenuIds) {
            final Optional<Menu> o_menu = menuService.findById(addMenuId);
            if (o_menu.isEmpty()) {
                addMenusDetail.append(addMenuId).append(", ");
                continue;
            }
            Menu menu = o_menu.get();
            if (!menus.contains(menu)) {
                menus.add(menu);
            }
        }
        for (Integer removeMenuId : removeMenuIds) {
            final Optional<Menu> o_menu = menuService.findById(removeMenuId);
            if (o_menu.isEmpty()) {
                removeMenusDetail.append(removeMenuId).append(", ");
                continue;
            }
            Menu menu = o_menu.get();
            if (menus.contains(menu)) {
                menus.remove(menu);
            }
        }
        try {
            roleDao.saveAndFlush(role);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.RoleService.updateRoleMenus.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.RoleService.updateRoleMenus.success");
    }
}
