package com.ysh.projectY.service;

import com.ysh.projectY.dao.LogsDao;
import com.ysh.projectY.entity.Logs;
import org.springframework.stereotype.Service;

@Service
public class LogsService {

    LogsDao logsDao;

    public LogsService(LogsDao logsDao) {
        this.logsDao = logsDao;
    }

    public void createLog(Logs logs) {
        logsDao.saveAndFlush(logs);
    }
}
