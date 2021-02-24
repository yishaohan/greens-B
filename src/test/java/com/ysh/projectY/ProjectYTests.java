package com.ysh.projectY;

import com.ysh.projectY.entity.*;
import com.ysh.projectY.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
class ProjectYTests {

    @Autowired
    BCSchoolsCOVID19Service bcSchoolsCOVID19Service;

    @Autowired
    SchoolsInfoService schoolsInfoService;

    @Autowired
    HealthRegionService healthRegionService;

    @Autowired
    SchoolDistrictService schoolDistrictService;

    @Autowired
    SchoolDistrictContactsService schoolDistrictContactsService;

    @Autowired
    CitiesInfoService citiesInfoService;

    //    @Test
    @Transactional(readOnly = true)
    void contextLoads() {
//        final List<CitiesInfo> citiesInfoList = citiesInfoService.findAll();
//        for (CitiesInfo citiesInfo : citiesInfoList) {
//            System.out.print("城市: " + citiesInfo.getCityName() + " --> ");
//            int sum = 0;
//            final Set<SchoolsInfo> schoolsInfo = citiesInfo.getSchoolsInfo();
//            for (SchoolsInfo info : schoolsInfo) {
//                sum += info.getBcSchoolsCOVID19().size();
//            }
//            System.out.println(sum);
//        }

        //////////////////////////////////////////////////////////////////////////
//        final List<HealthRegion> healthRegionList = healthRegionService.findAll();
//        for (HealthRegion healthRegion : healthRegionList) {
//            System.out.println(healthRegion.getHealthRegionName() + " --> " + healthRegion.getBcSchoolsCOVID19().size());
//        }

        //////////////////////////////////////////////////////////////////////////
//        final List<SchoolDistrict> schoolDistrictList = schoolDistrictService.findAll();
//        for (SchoolDistrict schoolDistrict : schoolDistrictList) {
////            System.out.print("教育局: " + schoolDistrict.getDistrictName() + " --> ");
//            int sum = 0;
//            final Set<SchoolsInfo> schoolsInfo = schoolDistrict.getSchoolsInfo();
//            for (SchoolsInfo info : schoolsInfo) {
//                sum += info.getBcSchoolsCOVID19().size();
//            }
//            System.out.println(sum);
//        }

        //////////////////////////////////////////////////////////////////////////
        final List<SchoolsInfo> schoolsInfoList = schoolsInfoService.findAll();
        for (SchoolsInfo schoolsInfo : schoolsInfoList) {
//            System.out.print("学校: " + schoolsInfo.getSchoolName() + " --> ");
            System.out.println(schoolsInfo.getBcSchoolsCOVID19().size());
        }
    }

    //    @Test
    @Transactional(readOnly = true)
    void testBCSchoolsCOVID19() {
//        根据ID查询COVID19
        int id = 8;
        System.out.println("1. ******************************************");
        final Optional<BCSchoolsCOVID19> optional = bcSchoolsCOVID19Service.findById(id);
        if (optional.isEmpty()) {
            System.out.println("BCSchoolsCOVID19: " + id + " 不存在!");
            return;
        }
        BCSchoolsCOVID19 bcSchoolsCOVID19 = optional.get();
        System.out.println(bcSchoolsCOVID19);
        System.out.println("2. ******************************************");
//      按学校统计COVID19
//        final List<SchoolsInfo> schoolsInfoList = schoolsInfoService.findAll();
//        for (SchoolsInfo schoolsInfo : schoolsInfoList) {
//            System.out.print("学校: " + schoolsInfo.getSchoolName() + " --> ");
//            final Set<BCSchoolsCOVID19> bySchoolsInfo = bcSchoolsCOVID19Service.findBySchoolsInfo(schoolsInfo);
//            System.out.println(bySchoolsInfo.size());
//        }

//        按卫生局统计COVID19
//        final List<HealthRegion> regionServiceAll = healthRegionService.findAll();
//        for (HealthRegion healthRegion : regionServiceAll) {
//            System.out.print("卫生局: " + healthRegion.getHealthRegionName() + " --> ");
//            final Set<BCSchoolsCOVID19> byHealthRegion = bcSchoolsCOVID19Service.findByHealthRegion(healthRegion);
//            System.out.println(byHealthRegion.size());
//        }

//        按教育局统计COVID19
//        System.out.println("1. ============================================================================");
//        final List<SchoolDistrict> schoolDistrictList = schoolDistrictService.findAll();
//        System.out.println("2. ============================================================================");
//        for (SchoolDistrict schoolDistrict : schoolDistrictList) {
//            System.out.print("教育局: " + schoolDistrict.getDistrictName() + " --> ");
//            int sum = 0;
//            final Set<SchoolsInfo> schoolsInfo = schoolDistrict.getSchoolsInfo();
//            for (SchoolsInfo info : schoolsInfo) {
//                sum += bcSchoolsCOVID19Service.countBySchoolsInfo(info);
//            }
//            System.out.println(sum);
//        }
//        System.out.println("3. ============================================================================");
    }

    @Test
    @Transactional(readOnly = true)
    void testCitiesInfo() {
//        int id = 117;
//        final Optional<CitiesInfo> optional = citiesInfoService.findById(id);
//        if (optional.isEmpty()) {
//            System.out.println("NULL");
//            return;
//        }
//        final CitiesInfo citiesInfo = optional.get();
//        System.out.println(citiesInfo);
//        final List<CitiesInfo> citiesInfoList = citiesInfoService.findAll();
//        for (CitiesInfo citiesInfo : citiesInfoList) {
//            System.out.println("***" + citiesInfo.getCityName() + "***");
//            final Set<SchoolsInfo> schoolsInfo = citiesInfo.getSchoolsInfo();
//            for (SchoolsInfo info : schoolsInfo) {
//                System.out.println(info.getSchoolName());
//            }
//        }
        final List<CitiesInfo> citiesInfoList = citiesInfoService.findAll();
        for (CitiesInfo citiesInfo : citiesInfoList) {
            System.out.println("***" + citiesInfo.getCityName() + "***");
            final Set<SchoolDistrict> schoolDistricts = citiesInfo.getSchoolDistricts();
            for (SchoolDistrict schoolDistrict : schoolDistricts) {
                System.out.println("\t" + schoolDistrict.getDistrictName());
            }
        }

    }

    //    @Test
    @Transactional(readOnly = true)
    void testHealthRegion() {
        int id = 1;
        final Optional<HealthRegion> optional = healthRegionService.findById(id);
        if (optional.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        final HealthRegion healthRegion = optional.get();
        System.out.println(healthRegion);
    }

    //    @Test
    @Transactional(readOnly = true)
    void testSchoolDistrict() {
        int id = 1;
        final Optional<SchoolDistrict> optional = schoolDistrictService.findById(1);
        if (optional.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        final SchoolDistrict schoolDistrict = optional.get();
        System.out.println(schoolDistrict);
    }

    //    @Test
    @Transactional(readOnly = true)
    void testSchoolDistrictContacts() {
        int id = 1;
        final Optional<SchoolDistrictContacts> optional = schoolDistrictContactsService.findById(id);
        if (optional.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        final SchoolDistrictContacts schoolDistrictContacts = optional.get();
        System.out.println(schoolDistrictContacts);
    }

    //    @Test
    @Transactional(readOnly = true)
    void testSchoolsInfo() {
        int id = 1;
        final Optional<SchoolsInfo> optional = schoolsInfoService.findById(id);
        if (optional.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        final SchoolsInfo schoolsInfo = optional.get();
        System.out.println(schoolsInfo);
    }
}
