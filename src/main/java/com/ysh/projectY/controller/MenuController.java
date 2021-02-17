package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.form.CreateMenu;
import com.ysh.projectY.form.UpdateMenu;
import com.ysh.projectY.service.MenuService;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class MenuController {

    final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/admin/menus")
    public HttpEntity<?> getMenus(@RequestParam(name = "menuName", defaultValue = "") String menuName,
                                  @RequestParam(name = "menuDescript", defaultValue = "") String menuDescript,
                                  @RequestParam(name = "menuPath", defaultValue = "") String menuPath,
                                  @RequestParam(name = "current", defaultValue = "1") int current,
                                  @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        if (current <= 0) {
            current = 1;
        }
        if (pageSize <= 0) {
            pageSize = 1;
        }
        if (pageSize > 1000) {
            pageSize = 1000;
        }
        Pageable pageable = PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        final Page<Menu> menus = menuService.getMenus(menuName, menuDescript, menuPath, pageable);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.MenuController.getMenus.success", menus, menus.toString()), HttpStatus.OK);
    }

    @GetMapping("/admin/menus/menuGrade")
    public HttpEntity<?> getHigherMenus(@RequestParam(name = "menuGrade", defaultValue = "0") int menuGrade) {
        final List<Menu> menus = menuService.findByMenuGradeLessThan(menuGrade);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.MenuController.getHigherMenus.success", menus), HttpStatus.OK);
    }

    @PutMapping("/admin/menus")
    public HttpEntity<?> updateMenu(@Valid @RequestBody UpdateMenu updateMenu) {
        final MethodResponse methodResponse = menuService.updateMenu(updateMenu);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/menus/{id}")
    public HttpEntity<?> deleteMenu(@PathVariable(name = "id") int id) {
        final MethodResponse methodResponse = menuService.deleteMenu(id);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @DeleteMapping("/admin/menus")
    public HttpEntity<?> deleteMenus(@Valid @RequestBody List<Integer> ids) {
        final MethodResponse methodResponse = menuService.deleteMenus(ids);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @PostMapping("/admin/menus")
    public HttpEntity<?> createMenu(@Valid @RequestBody CreateMenu createMenu) {
        final MethodResponse methodResponse = menuService.createMenu(createMenu);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }
}
