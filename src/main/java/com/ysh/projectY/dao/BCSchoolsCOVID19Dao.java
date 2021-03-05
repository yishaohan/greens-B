package com.ysh.projectY.dao;

import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.form.*;
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

    @Query(value = "select health_region_name as healthRegionName, count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by health_region_name order by health_region_name", nativeQuery = true)
    Set<BCSchoolsHealthsCOVID19Summary> countByHealthName(String startDate, String endDate);

    @Query(value = "select count(*) from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate", nativeQuery = true)
    int countByNotificationDate(String startDate, String endDate);

    @Query(value = "select 'All' as 'healthRegionName', notification_date as day,count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by notification_date order by notification_date", nativeQuery = true)
    Set<BCSchoolsCOVID19DailySummary> countByNotificationDateGroupByNotificationDate(String startDate, String endDate);

    @Query(value = "select health_region_name as healthRegionName, notification_date as day,count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by health_region_name,notification_date order by health_region_name,notification_date", nativeQuery = true)
    Set<BCSchoolsCOVID19DailySummary> countByHealthRegionNameNotificationDateGroupByNotificationDate(String startDate, String endDate);

    @Query(value = "select health_region_name as healthRegionName, city_name as cityName,count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by health_region_name,city_name order by health_region_name,city_name", nativeQuery = true)
    Set<BCSchoolsHealthsCitiesCOVID19Summary> countByHealthsCities(String startDate, String endDate);

    @Query(value = "select substr(notification_date, 1, 7) as month, count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate group by month order by month", nativeQuery = true)
    Set<BCSchoolsCOVID19MonthlySummary> countByNotificationDateMonthly(String startDate, String endDate);

    @Query(value = "select notification_date as day,count(*) as count from bc_school_covid19 where notification_date >=:startDate and notification_date<=:endDate and school_id=:schoolId group by notification_date order by notification_date", nativeQuery = true)
    Set<BCSchoolCOVID19DailySummary> countByNotificationDateGroupBySchoolId(String startDate, String endDate, int schoolId);
}
