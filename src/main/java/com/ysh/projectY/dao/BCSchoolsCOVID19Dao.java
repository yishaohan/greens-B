package com.ysh.projectY.dao;

import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.entity.HealthRegion;
import com.ysh.projectY.entity.SchoolsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface BCSchoolsCOVID19Dao extends JpaRepository<BCSchoolsCOVID19, Integer> {

    public Set<BCSchoolsCOVID19> findByHealthRegion(HealthRegion healthRegion);

    public Set<BCSchoolsCOVID19> findBySchoolsInfo(SchoolsInfo schoolsInfo);

    public int countBySchoolsInfo(SchoolsInfo schoolsInfo);
}
