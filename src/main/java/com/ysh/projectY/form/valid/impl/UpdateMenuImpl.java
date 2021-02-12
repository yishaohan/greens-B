package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.form.valid.UpdateMenu;
import com.ysh.projectY.service.MenuService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdateMenuImpl implements ConstraintValidator<UpdateMenu, com.ysh.projectY.form.UpdateMenu> {

//    MenuService menuService;
//
//    public UpdateMenuImpl(MenuService menuService) {
//        this.menuService = menuService;
//    }

    @Override
    public boolean isValid(com.ysh.projectY.form.UpdateMenu updateMenu, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        final Integer parentId = updateMenu.getParentId();
        final Integer menuGrade = updateMenu.getMenuGrade();
        final String menuPath = updateMenu.getMenuPath();
        final String menuComponent = updateMenu.getMenuComponent();

        if (updateMenu.getId().equals(updateMenu.getParentId())) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateMenu.parentId.incorrect-value").addConstraintViolation();
            return false;
        }
        if (!(menuGrade == 1 || menuGrade == 2)) {
            context.buildConstraintViolationWithTemplate("projectY.valid.UpdateMenu.menuGrade.incorrect-value").addConstraintViolation();
            return false;
        }
        if (menuGrade == 1) {
            if (parentId != 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateMenu.menuGrade.parent-id-incorrect-value").addConstraintViolation();
                return false;
            }
        } else {
            if (parentId == 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.UpdateMenu.menuGrade.parent-id-incorrect-value").addConstraintViolation();
                return false;
            }
        }
        if (!menuPath.startsWith("/")) {
            context.buildConstraintViolationWithTemplate("projectY.valid.CreateMenu.menuPath.incorrect-value").addConstraintViolation();
            return false;
        }
        if (!menuComponent.startsWith("/")) {
            context.buildConstraintViolationWithTemplate("projectY.valid.CreateMenu.menuComponent.incorrect-value").addConstraintViolation();
            return false;
        }
        return true;
    }
}

