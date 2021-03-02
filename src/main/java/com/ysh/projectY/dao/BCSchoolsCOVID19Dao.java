package com.ysh.projectY.dao;

import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.form.BCSchoolsDistrictsCOVID19Summary;
import com.ysh.projectY.form.BCSchoolsHealthsCOVID19Summary;
import com.ysh.projectY.form.BCSchoolsCOVID19Summary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface BCSchoolsCOVID19Dao extends JpaRepository<BCSchoolsCOVID19, Integer> {

//    public Set<BCSchoolsCOVID19> findByHealthRegion(HealthRegion healthRegion);

//    public Set<BCSchoolsCOVID19> findBySchoolsInfo(SchoolsInfo schoolsInfo);
//
//    public int countBySchoolsInfo(SchoolsInfo schoolsInfo);

    public Page<BCSchoolsCOVID19> findAllBySchoolNameContainingAndNotificationDateContainingAndDistrictAbbContainingAndNotificationMethodContainingAndHealthRegionNameContainingAndCityNameContaining(String schoolName, String notificationDate, String districtAbb, String notificationMethod, String healthRegionName, String cityName, Pageable pageable);

    @Query(value = "select district_abb as districtAbb, district_name as districtName, count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by district_abb, district_name order by district_abb", nativeQuery = true)
    Set<BCSchoolsDistrictsCOVID19Summary> countByDistrictAbb(String startDate, String endDate);

    @Query(value = "select school_name as schoolName, count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by school_name order by count desc", nativeQuery = true)
    Set<BCSchoolsCOVID19Summary> countBySchoolName(String startDate, String endDate);

    @Query(value = "select health_region_name as healthName, count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by health_region_name order by count desc", nativeQuery = true)
    Set<BCSchoolsHealthsCOVID19Summary> countByHealthName(String startDate, String endDate);

    @Query(value = "select count(*) from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate", nativeQuery = true)
    int countByNotificationDate(String startDate, String endDate);
}
