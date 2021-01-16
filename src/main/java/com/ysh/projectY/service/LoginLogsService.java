package com.ysh.projectY.service;

import com.ysh.projectY.dao.LoginLogsDao;
import com.ysh.projectY.entity.LoginLogs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogsService {
    final LoginLogsDao loginLogsDao;

    public LoginLogsService(LoginLogsDao loginLogsDao) {
        this.loginLogsDao = loginLogsDao;
    }

    public void saveAndFlush(LoginLogs loginLogs) {
        loginLogsDao.saveAndFlush(loginLogs);
    }

    public void saveAll(List<LoginLogs> loginLogs) {
        loginLogsDao.saveAll(loginLogs);
    }

    public List<LoginLogs> findBySessionID(String sessionID) {
        return loginLogsDao.findBySessionID(sessionID);
    }

    public int findLastFailureCountByUsername(String username) {
        return loginLogsDao.findLastFailureCountByUsername(username);
    }

    public int findLastFailureCountByMobilePhone(String mobilePhone) {
        return loginLogsDao.findLastFailureCountByMobilePhone(mobilePhone);
    }
}
