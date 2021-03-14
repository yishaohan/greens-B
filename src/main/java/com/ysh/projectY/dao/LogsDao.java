package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsDao extends JpaRepository<Logs, Integer>, JpaSpecificationExecutor {
}
