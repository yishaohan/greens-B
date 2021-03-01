package com.ysh.projectY.service;

import com.ysh.projectY.dao.BCSchoolsCOVID19Dao;
import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.entity.HealthRegion;
import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.entity.SchoolsInfo;
import com.ysh.projectY.form.BCDistrictsCOVID19Summary;
import com.ysh.projectY.form.BCHealthsCOVID19Summary;
import com.ysh.projectY.form.BCSchoolsCOVID19Summary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Service
public class BCSchoolsCOVID19Service {

    final BCSchoolsCOVID19Dao bcSchoolsCOVID19Dao;

    public BCSchoolsCOVID19Service(BCSchoolsCOVID19Dao bcSchoolsCOVID19Dao) {
        this.bcSchoolsCOVID19Dao = bcSchoolsCOVID19Dao;
    }

    public void saveAndFlush(BCSchoolsCOVID19 bcSchoolsCOVID19) {
        bcSchoolsCOVID19Dao.saveAndFlush(bcSchoolsCOVID19);
    }

    public Optional<BCSchoolsCOVID19> findById(int id) {
        return bcSchoolsCOVID19Dao.findById(id);
    }

//    public Set<BCSchoolsCOVID19> findByHealthRegion(HealthRegion healthRegion) {
//        return bcSchoolsCOVID19Dao.findByHealthRegion(healthRegion);
//    }

//    public Set<BCSchoolsCOVID19> findBySchoolsInfo(SchoolsInfo schoolsInfo) {
//        return bcSchoolsCOVID19Dao.findBySchoolsInfo(schoolsInfo);
//    }

//    public int countBySchoolsInfo(SchoolsInfo schoolsInfo) {
//        return bcSchoolsCOVID19Dao.countBySchoolsInfo(schoolsInfo);
//    }

    public Page<BCSchoolsCOVID19> getBCSchoolsCOVID19(String schoolName, String notificationDate, String districtAbb, String notificationMethod, String healthRegionName, String cityName, Pageable pageable) {
        final Page<BCSchoolsCOVID19> page = bcSchoolsCOVID19Dao.findAllBySchoolNameContainingAndNotificationDateContainingAndDistrictAbbContainingAndNotificationMethodContainingAndHealthRegionNameContainingAndCityNameContaining(schoolName, notificationDate, districtAbb, notificationMethod, healthRegionName, cityName, pageable);
        return page;
    }

    public Set<BCDistrictsCOVID19Summary> getBCDistrictsCOVID19Summary(String startDate, String endDate) {
        return bcSchoolsCOVID19Dao.countByDistrictAbb(startDate, endDate);
    }

    public Set<BCSchoolsCOVID19Summary> getBCSchoolsCOVID19Summary(String startDate, String endDate) {
        return bcSchoolsCOVID19Dao.countBySchoolName(startDate, endDate);
    }

    public Set<BCHealthsCOVID19Summary> getBCHealthsCOVID19Summary(String startDate, String endDate) {
        return bcSchoolsCOVID19Dao.countByHealthName(startDate, endDate);
    }
}
