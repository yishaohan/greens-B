package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Authorization;
import com.ysh.projectY.form.CreateAuth;
import com.ysh.projectY.form.UpdateAuth;
import com.ysh.projectY.service.AuthorizationService;
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
public class AuthorizationController {

    final AuthorizationService authService;

    public AuthorizationController(AuthorizationService authService) {
        this.authService = authService;
    }

    @GetMapping("/admin/auths")
    public HttpEntity<?> getAuths(@RequestParam(name = "authName", defaultValue = "") String authName,
                                  @RequestParam(name = "requestMethod", defaultValue = "") String requestMethod,
                                  @RequestParam(name = "requestUrl", defaultValue = "") String requestUrl,
                                  @RequestParam(name = "current", defaultValue = "1") int current,
                                  @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        final Page<Authorization> auths = authService.getAuths(authName, requestMethod, requestUrl, pageable);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.AuthController.getAuths.success", auths, auths.toString()), HttpStatus.OK);
    }

    @GetMapping("/admin/auths/authGrade")
    public HttpEntity<?> getHigherAuths(@RequestParam(name = "authGrade", defaultValue = "0") int authGrade) {
        final List<Authorization> auths = authService.findByAuthGradeLessThan(authGrade);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.AuthController.getHigherAuths.success", auths), HttpStatus.OK);
    }

    @PutMapping("/admin/auths")
    public HttpEntity<?> updateAuth(@Valid @RequestBody UpdateAuth updateAuth) {
        final MethodResponse methodResponse = authService.updateAuth(updateAuth);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/auths/{id}")
    public HttpEntity<?> deleteAuth(@PathVariable(name = "id") int id) {
        final MethodResponse methodResponse = authService.deleteAuth(id);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @DeleteMapping("/admin/auths")
    public HttpEntity<?> deleteAuths(@Valid @RequestBody List<Integer> ids) {
        final MethodResponse methodResponse = authService.deleteAuths(ids);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.NO_CONTENT.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
    }

    @PostMapping("/admin/auths")
    public HttpEntity<?> createAuth(@Valid @RequestBody CreateAuth createAuth) {
        final MethodResponse methodResponse = authService.createAuth(createAuth);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

}
