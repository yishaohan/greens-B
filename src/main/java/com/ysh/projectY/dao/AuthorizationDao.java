package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationDao extends JpaRepository<Authorization, Integer> {
    Page<Authorization> findAllByAuthNameContainingAndRequestMethodContainingAndRequestUrlContaining(String authName, String requestMethod, String requestUrl, Pageable pageable);

    List<Authorization> findByAuthGradeLessThan(int authGrade);
}
