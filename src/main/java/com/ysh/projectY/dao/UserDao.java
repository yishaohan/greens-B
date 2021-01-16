package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Role;
import com.ysh.projectY.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByMobilePhone(String mobilePhone);

    @Query(value = "select id, username, mobile_phone, password, nickname, avatar_url, create_date_time, user_enabled, user_locked from project_y.user where username=:username", nativeQuery = true)
    User loadUserByUserName(String username);

    @Query(value = "select id, username, mobile_phone, password, nickname, avatar_url, create_date_time, user_enabled, user_locked from project_y.user where mobile_phone=:mobilePhone", nativeQuery = true)
    User loadUserByMobilePhone(String mobilePhone);

//    @Query(value = "select r.* from project_y.role r,project_y.user_role ur where r.id=ur.role_id and ur.user_id=:userID", nativeQuery = true)
//    List<Role> getUserRolesByUserId(int userID);

    @Query(value = "select count(*) from project_y.user where mobile_phone=:mobilePhone", nativeQuery = true)
    int isExistByMobilePhone(String mobilePhone);
}
