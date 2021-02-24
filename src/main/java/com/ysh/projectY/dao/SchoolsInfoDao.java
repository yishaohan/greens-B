package com.ysh.projectY.dao;

import com.ysh.projectY.entity.SchoolsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SchoolsInfoDao extends JpaRepository<SchoolsInfo, Integer> {
    public Optional<SchoolsInfo> findBySchoolName(String schoolName);
}
