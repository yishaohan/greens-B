package com.ysh.projectY.dao;

import com.ysh.projectY.entity.LoginLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoginLogsDao extends JpaRepository<LoginLogs, Integer> {
    List<LoginLogs> findBySessionID(String sessionID);

    @Query(value = "select count(*) from login_logs where `status`='FAILURE' and username=:username and id>(select ifnull(max(id),0) from login_logs where status='SUCCESS' and username=:username)", nativeQuery = true)
    int findLastFailureCountByUsername(String username);

    @Query(value = "select count(*) from login_logs where `status`='FAILURE' and mobile_phone=:mobilePhone and id>(select ifnull(max(id),0) from login_logs where status='SUCCESS' and mobile_phone=:mobilePhone)", nativeQuery = true)
    int findLastFailureCountByMobilePhone(String mobilePhone);
}
