package com.ysh.projectY.form.valid.impl;

import com.ysh.projectY.form.valid.CreateMenu;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreateMenuImpl implements ConstraintValidator<CreateMenu, com.ysh.projectY.form.CreateMenu> {

//    MenuService menuService;
//
//    public CreateMenuImpl(MenuService menuService) {
//        this.menuService = menuService;
//    }

    @Override
    public boolean isValid(com.ysh.projectY.form.CreateMenu createMenu, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        final Integer parentId = createMenu.getParentId();
        final Integer menuGrade = createMenu.getMenuGrade();
        final String menuPath = createMenu.getMenuPath();
        final String menuComponent = createMenu.getMenuComponent();
        if (!(menuGrade == 1 || menuGrade == 2)) {
            context.buildConstraintViolationWithTemplate("projectY.valid.CreateMenu.menuGrade.incorrect-value").addConstraintViolation();
            return false;
        }
        if (menuGrade == 1) {
            if (parentId != 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.CreateMenu.menuGrade.parent-id-incorrect-value").addConstraintViolation();
                return false;
            }
        } else {
            if (parentId == 0) {
                context.buildConstraintViolationWithTemplate("projectY.valid.CreateMenu.menuGrade.parent-id-incorrect-value").addConstraintViolation();
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

