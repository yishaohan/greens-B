package com.ysh.projectY.service;

import com.ysh.projectY.dao.MessageDao;
import com.ysh.projectY.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageDao messageDao;

    public void save(Message message) {
        messageDao.save(message);
    }

}

