package com.ysh.projectY.dao;

import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.entity.HealthRegion;
import com.ysh.projectY.entity.SchoolsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Set;


@Repository
public interface BCSchoolsCOVID19Dao extends JpaRepository<BCSchoolsCOVID19, Integer> {

//    public Set<BCSchoolsCOVID19> findByHealthRegion(HealthRegion healthRegion);

//    public Set<BCSchoolsCOVID19> findBySchoolsInfo(SchoolsInfo schoolsInfo);
//
//    public int countBySchoolsInfo(SchoolsInfo schoolsInfo);

    public Page<BCSchoolsCOVID19> findAllBySchoolNameContainingAndNotificationDateContainingAndDistrictAbbContainingAndNotificationMethodContainingAndHealthRegionNameContainingAndCityNameContaining(String schoolName, String notificationDate, String districtAbb, String notificationMethod, String healthRegionName, String cityName, Pageable pageable);
}
