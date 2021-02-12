package com.ysh.projectY.service;

import com.ysh.projectY.dao.MenuDao;
import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.form.CreateMenu;
import com.ysh.projectY.form.UpdateMenu;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    final MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public Optional<Menu> findById(int Id) {
        return menuDao.findById(Id);
    }

    public Page<Menu> getMenus(String menuName, String menuDescript, String menuPath, Pageable pageable) {
        final Page<Menu> page = menuDao.findAllByMenuNameContainingAndMenuDescriptContainingAndMenuPathContaining(menuName, menuDescript, menuPath, pageable);
        return page;
    }

    public MethodResponse updateMenu(UpdateMenu updateMenu) {
        final Integer parentId = updateMenu.getParentId();
        final Optional<Menu> o_menu = menuDao.findById(updateMenu.getId());
        if (o_menu.isEmpty()) {
            return MethodResponse.failure("projectY.MenuService.updateMenu.failure.id-not-exist");
        }
        final Optional<Menu> o_parent = menuDao.findById(parentId);
        if (parentId != 0) {
            if (o_parent.isEmpty()) {
                return MethodResponse.failure("projectY.MenuService.updateMenu.failure.parentId-not-exist");
            }
        }
        Menu menu = o_menu.get();
        menu.setParentId(parentId);
        menu.setMenuGrade(updateMenu.getMenuGrade());
        menu.setSortId(updateMenu.getSortId());
        if (!menu.getMenuName().equals(updateMenu.getMenuName())) {
            final Optional<Menu> o_menuName = menuDao.findByMenuName(updateMenu.getMenuName());
            if (o_menuName.isPresent()) {
                return MethodResponse.failure("projectY.MenuService.updateMenu.failure.menuName-exist");
            }
        }
        menu.setMenuIcon(updateMenu.getMenuIcon());
        menu.setMenuName(updateMenu.getMenuName());
        menu.setMenuDescript(updateMenu.getMenuDescript());
        menu.setMenuPath(updateMenu.getMenuPath());
        menu.setMenuComponent(updateMenu.getMenuComponent());
        menu.setUpdateDateTime(new Timestamp(System.currentTimeMillis()));
        Boolean newEnabled = updateMenu.getEnabled();
        if (newEnabled != null) {
            menu.setEnabled(newEnabled);
        }
        try {
            menuDao.saveAndFlush(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.MenuService.updateMenu.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.MenuService.updateMenu.success");
    }

    public MethodResponse deleteMenu(int id) {
        final Optional<Menu> optional = menuDao.findById(id);
        if (optional.isEmpty()) {
            return MethodResponse.failure("projectY.MenuService.deleteMenu.failure.id-not-exist");
        }
//        if (menuDao.findUsedCountByMenuId(id) > 0) {
//            return MethodResponse.failure("projectY.MenuService.deleteMenu.failure.menu-is-used");
//        }
        menuDao.delete(optional.get());
        return MethodResponse.success("projectY.MenuService.deleteMenu.success");
    }

    public MethodResponse deleteMenus(List<Integer> ids) {
        StringBuilder menuNotExist = new StringBuilder();
        StringBuilder menuIsUsed = new StringBuilder();
        for (Integer id : ids) {
            final Optional<Menu> optional = menuDao.findById(id);
            if (optional.isEmpty()) {
                menuNotExist.append(id).append(", ");
                continue;
            }
//            if (menuDao.findUsedCountByMenuId(id) > 0) {
//                menuIsUsed.append(id).append(", ");
//                continue;
//            }
            menuDao.delete(optional.get());
        }
        if (!"".equals(menuIsUsed.toString())) {
            menuIsUsed = new StringBuilder("[ " + menuIsUsed.substring(0, menuIsUsed.length() - 2) + " ] Menu is Used");
            return MethodResponse.failure("projectY.MenuService.deleteMenus.failure.ids-is-used", menuIsUsed.toString(), null);
        }
        if (!"".equals(menuNotExist.toString())) {
            menuNotExist = new StringBuilder("[ " + menuNotExist.substring(0, menuNotExist.length() - 2) + " ] Menu not Exist; ");
            return MethodResponse.failure("projectY.MenuService.deleteMenus.failure.ids-not-exist", menuNotExist.toString(), null);
        }
        return MethodResponse.success("projectY.MenuService.deleteMenus.success");
    }

    public MethodResponse createMenu(CreateMenu createMenu) {
        final Integer parentId = createMenu.getParentId();
        final String menuName = createMenu.getMenuName();
        final Optional<Menu> o_parent = menuDao.findById(parentId);
        if (parentId != 0) {
            if (o_parent.isEmpty()) {
                return MethodResponse.failure("projectY.valid.CreateMenu.parentId.not-exist");
            }
            if (o_parent.get().getMenuGrade() != 1) {
                return MethodResponse.failure("projectY.valid.CreateMenu.parentId.incorrect-value");
            }
        }
        final Optional<Menu> o_menu = menuDao.findByMenuName(menuName);
        if (o_menu.isPresent()) {
            return MethodResponse.failure("projectY.valid.CreateMenu.menuName.exist");
        }
        Menu menu = new Menu();
        menu.setParentId(parentId);
        menu.setMenuGrade(createMenu.getMenuGrade());
        menu.setSortId(createMenu.getSortId());
        menu.setMenuIcon(createMenu.getMenuIcon());
        menu.setMenuName(createMenu.getMenuName());
        menu.setMenuDescript(createMenu.getMenuDescript());
        menu.setMenuPath(createMenu.getMenuPath());
        menu.setMenuComponent(createMenu.getMenuComponent());
        final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        menu.setCreateDateTime(currentTimestamp);
        menu.setUpdateDateTime(currentTimestamp);
        menu.setEnabled(true);
        try {
            menuDao.saveAndFlush(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.MenuService.createMenu.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.MenuService.createMenu.success");
    }

    public Optional<Menu> findByMenuName(String menuName) {
        return menuDao.findByMenuName(menuName);
    }

    public List<Menu> findByMenuGradeLessThan(int menuGrade) {
        return menuDao.findByMenuGradeLessThan(menuGrade);
    }

}
