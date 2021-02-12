package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.entity.Role;
import com.ysh.projectY.form.valid.UpdateRoleMenus;
import com.ysh.projectY.service.MenuService;
import com.ysh.projectY.service.RoleService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class UpdateRoleMenusImpl implements ConstraintValidator<UpdateRoleMenus, com.ysh.projectY.form.UpdateRoleMenus> {

    RoleService roleService;
    MenuService menuService;

    public UpdateRoleMenusImpl(RoleService roleService, MenuService menuService) {
        this.roleService = roleService;
        this.menuService = menuService;
    }

    @Override
    public boolean isValid(com.ysh.projectY.form.UpdateRoleMenus updateRoleMenus, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        StringBuilder addMenusDetail = new StringBuilder();
        StringBuilder removeMenusDetail = new StringBuilder();
        final List<Integer> addMenuIds = updateRoleMenus.getAddMenuIds();
        final List<Integer> removeMenuIds = updateRoleMenus.getRemoveMenuIds();
        final Iterator<Integer> i_addMenuIds = addMenuIds.iterator();
        while (i_addMenuIds.hasNext()) {
            int id = i_addMenuIds.next();
            final Optional<Menu> optional = menuService.findById(id);
            if (optional.isEmpty()) {
                i_addMenuIds.remove();
                addMenusDetail.append(id).append(", ");
            }
        }
        final Iterator<Integer> i_removeMenuIds = removeMenuIds.iterator();
        while (i_removeMenuIds.hasNext()) {
            int id = i_removeMenuIds.next();
            final Optional<Menu> optional = menuService.findById(id);
            if (optional.isEmpty()) {
                i_removeMenuIds.remove();
                removeMenusDetail.append(id).append(", ");
            }
        }
        if (!"".equals(addMenusDetail.toString())) {
            System.out.println("   Add: [ " + addMenusDetail.substring(0, addMenusDetail.length() - 2) + " ] Menuorization not Exist");
        }
        if (!"".equals(removeMenusDetail.toString())) {
            System.out.println("Remove: [ " + addMenusDetail.substring(0, addMenusDetail.length() - 2) + " ] Menuorization not Exist");
        }
        final Optional<Role> optional = roleService.findById(updateRoleMenus.getRoleId());
        if (optional.isEmpty()) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateRoleMenus.roleId.not-exist").addConstraintViolation();
            return false;
        }
        return true;
    }
}

