package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Role;
import com.ysh.projectY.form.CreateRole;
import com.ysh.projectY.form.UpdateRole;
import com.ysh.projectY.service.RoleService;
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
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/roles")
    public HttpEntity<?> getRoles(@RequestParam(name = "roleName", defaultValue = "") String roleName,
                                  @RequestParam(name = "roleDescript", defaultValue = "") String roleDescript,
                                  @RequestParam(name = "current", defaultValue = "1") int current,
                                  @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        final Page<Role> roles = roleService.getRoles(roleName, roleDescript, pageable);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.RoleController.getRoles.success", roles, roles.toString()), HttpStatus.OK);
    }

    @PutMapping("/admin/roles")
    public HttpEntity<?> updateRole(@Valid @RequestBody UpdateRole updateRole) {
        final MethodResponse methodResponse = roleService.updateRole(updateRole);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/roles/{id}")
    public HttpEntity<?> deleteRole(@PathVariable(name = "id") int id) {
        final MethodResponse methodResponse = roleService.deleteRole(id);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @DeleteMapping("/admin/roles")
    public HttpEntity<?> deleteRoles(@Valid @RequestBody List<Integer> ids) {
        final MethodResponse methodResponse = roleService.deleteRoles(ids);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @PostMapping("/admin/roles")
    public HttpEntity<?> createRole(@Valid @RequestBody CreateRole createRole) {
        final MethodResponse methodResponse = roleService.createRole(createRole);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }
}
