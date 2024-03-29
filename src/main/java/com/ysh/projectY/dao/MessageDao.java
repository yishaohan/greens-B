package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer>, JpaSpecificationExecutor {

}
