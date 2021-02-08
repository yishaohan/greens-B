package com.ysh.projectY.service;

import com.ysh.projectY.dao.AuthorizationDao;
import com.ysh.projectY.entity.Authorization;
import com.ysh.projectY.form.CreateAuth;
import com.ysh.projectY.form.UpdateAuth;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationService {

    final AuthorizationDao authDao;

    public AuthorizationService(AuthorizationDao authDao) {
        this.authDao = authDao;
    }

    public Page<Authorization> getAuths(String authName, String requestMethod, String requestUrl, Pageable pageable) {
        final Page<Authorization> page = authDao.findAllByAuthNameContainingAndRequestMethodContainingAndRequestUrlContaining(authName, requestMethod, requestUrl, pageable);
        return page;
    }

    public MethodResponse updateAuth(UpdateAuth updateAuth) {
        final Optional<Authorization> optional = authDao.findById(updateAuth.getId());
        Authorization auth = optional.get();
        auth.setParentId(updateAuth.getParentId());
        auth.setAuthGrade(updateAuth.getAuthGrade());
        auth.setAuthName(updateAuth.getAuthName());
        String newAuthDescript = updateAuth.getAuthDescript();
        if (newAuthDescript != null && !"".equals(newAuthDescript)) {
            auth.setAuthDescript(newAuthDescript);
        }
        auth.setRequestUrl(updateAuth.getRequestUrl());
        auth.setRequestMethod(updateAuth.getRequestMethod());
        auth.setUpdateDateTime(new Timestamp(System.currentTimeMillis()));
        Boolean newEnabled = updateAuth.getEnabled();
        if (newEnabled != null) {
            auth.setEnabled(newEnabled);
        }
        try {
            authDao.saveAndFlush(auth);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.AuthorizationService.updateAuth.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.AuthorizationService.updateAuth.success");
    }

    public Optional<Authorization> findById(int id) {
        return authDao.findById(id);
    }

    public MethodResponse deleteAuth(int id) {
        final Optional<Authorization> optional = authDao.findById(id);
        if (optional.isEmpty()) {
            return MethodResponse.failure("projectY.AuthService.deleteAuth.failure.id-not-exist");
        }
        authDao.delete(optional.get());
        return MethodResponse.success("projectY.AuthService.deleteAuth.success");
    }

    public MethodResponse deleteAuths(List<Integer> ids) {
        StringBuilder detail = new StringBuilder();
        for (Integer id : ids) {
            final Optional<Authorization> optional = authDao.findById(id);
            if (optional.isEmpty()) {
                detail.append(id).append(", ");
                continue;
            }
            authDao.delete(optional.get());
        }
        if (!"".equals(detail.toString())) {
            detail = new StringBuilder("[ " + detail.substring(0, detail.length() - 2) + " ] Authorization not Exist");
            return MethodResponse.failure("projectY.AuthService.deleteAuths.failure.ids-not-exist", detail.toString(), null);
        }
        return MethodResponse.success("projectY.AuthService.deleteAuths.success");
    }

    public MethodResponse createAuth(CreateAuth createAuth) {
        Authorization auth = new Authorization();
        auth.setParentId(createAuth.getParentId());
        auth.setAuthGrade(createAuth.getAuthGrade());
        auth.setAuthName(createAuth.getAuthName());
        auth.setAuthDescript(createAuth.getAuthDescript());
        auth.setRequestUrl(createAuth.getRequestUrl());
        auth.setRequestMethod(createAuth.getRequestMethod());
        auth.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
        auth.setUpdateDateTime(new Timestamp(System.currentTimeMillis()));
        auth.setEnabled(true);
        try {
            authDao.saveAndFlush(auth);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.AuthService.createAuth.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.AuthService.createAuth.success");
    }

    public List<Authorization> findByAuthGradeLessThan(int authGrade) {
        return authDao.findByAuthGradeLessThan(authGrade);
    }
}
