package com.ysh.projectY.service;

import com.ysh.projectY.dao.BCSchoolsCOVID19Dao;
import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.form.BCSchoolsCOVID19TotalSummary;
import com.ysh.projectY.form.BCSchoolsDistrictsCOVID19Summary;
import com.ysh.projectY.form.BCSchoolsHealthsCOVID19Summary;
import com.ysh.projectY.form.BCSchoolsCOVID19Summary;
import com.ysh.projectY.utils.dateTools;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public Set<BCSchoolsDistrictsCOVID19Summary> getBCSchoolsDistrictsCOVID19Summary(String startDate, String endDate) {
        return bcSchoolsCOVID19Dao.countByDistrictAbb(startDate, endDate);
    }

    public Set<BCSchoolsCOVID19Summary> getBCSchoolsCOVID19Summary(String startDate, String endDate) {
        return bcSchoolsCOVID19Dao.countBySchoolName(startDate, endDate);
    }

    public Set<BCSchoolsHealthsCOVID19Summary> getBCSchoolsHealthsCOVID19Summary(String startDate, String endDate) {
        return bcSchoolsCOVID19Dao.countByHealthName(startDate, endDate);
    }

    public Optional<BCSchoolsCOVID19TotalSummary> getBCSchoolsCOVID19TotalSummary() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BCSchoolsCOVID19TotalSummary bcSchoolsCOVID19TotalSummary = new BCSchoolsCOVID19TotalSummary();
        final long total = bcSchoolsCOVID19Dao.count();
        bcSchoolsCOVID19TotalSummary.setTotal(total);
        String updateDate = "2021-02-18";
        bcSchoolsCOVID19TotalSummary.setUpdateDateTime(updateDate);
        Date temp = null;
        try {
            temp = sdf.parse("2021-02-16");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateTime = dateTools.getThisWeekMonday(temp);
        String startDate = sdf.format(dateTools.getThisWeekMonday(dateTime));
        final Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.add(Calendar.DATE, 6);
        String endDate = sdf.format(cal.getTime());
        System.out.println(startDate + " / " + endDate);
        final float thisWeek = bcSchoolsCOVID19Dao.countByNotificationDate(startDate, endDate);
        cal.add(Calendar.DATE, -7);
        endDate = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, -6);
        startDate = sdf.format(cal.getTime());
        System.out.println(startDate + " / " + endDate);
        final float lastWeek = bcSchoolsCOVID19Dao.countByNotificationDate(startDate, endDate);
        bcSchoolsCOVID19TotalSummary.setWeeklyChanges(String.valueOf(Math.round(((thisWeek - lastWeek) / lastWeek) * 100)).replace(".0", ""));
        cal.setTime(temp);
        startDate = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        endDate = sdf.format(cal.getTime());
        System.out.println(startDate + "/ " + endDate);
        final float today = bcSchoolsCOVID19Dao.countByNotificationDate(startDate, startDate);
        final float yesterday = bcSchoolsCOVID19Dao.countByNotificationDate(endDate, endDate);
        bcSchoolsCOVID19TotalSummary.setDailyChanges(String.valueOf(Math.round(((today - yesterday) / yesterday) * 100)).replace(".0", ""));
        return Optional.of(bcSchoolsCOVID19TotalSummary);
    }
}
