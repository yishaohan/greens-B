package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query(value = "select r.* from project_y.role r,project_y.user_role ur where r.id=ur.role_id and ur.user_id=:userID", nativeQuery = true)
    List<Role> getUserRolesByUserId(int userID);
}
