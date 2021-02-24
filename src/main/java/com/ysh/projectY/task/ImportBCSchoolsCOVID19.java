package com.ysh.projectY.task;

import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.entity.BCSchoolsCOVID19TempRecord;
import com.ysh.projectY.entity.SchoolsInfo;
import com.ysh.projectY.entity.HealthRegion;
import com.ysh.projectY.service.BCSchoolsCOVID19Service;
import com.ysh.projectY.service.BCSchoolsCOVID19TempRecordService;
import com.ysh.projectY.service.SchoolsInfoService;
import com.ysh.projectY.service.HealthRegionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Component
public class ImportBCSchoolsCOVID19 {

    final BCSchoolsCOVID19TempRecordService bcSchoolsCOVID19TempRecordService;
    final BCSchoolsCOVID19Service bcSchoolsCOVID19Service;
    final SchoolsInfoService schoolsInfoService;
    final HealthRegionService healthRegionService;

    public ImportBCSchoolsCOVID19(BCSchoolsCOVID19TempRecordService bcSchoolsCOVID19TempRecordService, BCSchoolsCOVID19Service bcSchoolsCOVID19Service, SchoolsInfoService schoolsInfoService, final HealthRegionService healthRegionService) {
        this.bcSchoolsCOVID19TempRecordService = bcSchoolsCOVID19TempRecordService;
        this.bcSchoolsCOVID19Service = bcSchoolsCOVID19Service;
        this.schoolsInfoService = schoolsInfoService;
        this.healthRegionService = healthRegionService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void importBCSchoolsCOVID19Info() {
        final int totalRecords = bcSchoolsCOVID19TempRecordService.findAllCount();
        if (totalRecords == 0) {
            System.out.println("没有记录需要导入");
        }
        String errorMessage = "";
        Timestamp notificationDate;
        String schoolName;
        SchoolsInfo schoolsInfo;
        HealthRegion healthRegion;
        String notificationMethod;
        String exposureDate;
        int exposureNumber = 0;
        String extraInfo;
        String documentation;
        int i = 1;
        for (; i < totalRecords; i++) {
            final Optional<BCSchoolsCOVID19TempRecord> optional = bcSchoolsCOVID19TempRecordService.findByMinId();
            if (optional.isEmpty()) {
                errorMessage = "任务异常完成";
                break;
            }
            BCSchoolsCOVID19TempRecord tempRecord = optional.get();
            String tempNotificationDate = tempRecord.getNotificationDate();
            if (tempNotificationDate == null || "".equals(tempNotificationDate)) {
                errorMessage = "通知日期为空";
                break;
            }
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                notificationDate = new Timestamp(df.parse(tempNotificationDate).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                break;
            }
            schoolName = tempRecord.getSchoolName();
            Optional<SchoolsInfo> o_bcSchoolsInfo = schoolsInfoService.findBySchoolName(schoolName);
            if (o_bcSchoolsInfo.isEmpty()) {
                errorMessage = "找不到学校信息";
                break;
            }
            schoolsInfo = o_bcSchoolsInfo.get();
            String tempHealthRegion = tempRecord.getHealthRegion();
            final Optional<HealthRegion> o_healthRegion = healthRegionService.findByHealthRegionName(tempHealthRegion);
            if (o_healthRegion.isEmpty()) {
                errorMessage = "找不到卫生局信息";
                break;
            }
            healthRegion = o_healthRegion.get();
            notificationMethod = tempRecord.getNotificationMethod();
            if (notificationMethod == null || "".equals(notificationMethod)) {
                notificationMethod = "NULL";
            }
            exposureDate = tempRecord.getExposureDate();
            if (exposureDate == null || "".equals(exposureDate)) {
                exposureDate = "NULL";
            }
            extraInfo = tempRecord.getExtraInfo();
            if (extraInfo == null || "".equals(extraInfo)) {
                extraInfo = "NULL";
            } else {
                String tempExposureNumber = "";
                int index = extraInfo.indexOf(":");
                if (index != -1) {
                    tempExposureNumber = extraInfo.substring(0, index);
                    extraInfo = extraInfo.substring(index + 1);
                    tempExposureNumber = tempExposureNumber.replace("Exposure", "");
                } else {
                    tempExposureNumber = extraInfo.replace("Exposure", "");
                }
                tempExposureNumber = tempExposureNumber.replaceAll(" ", "");
                try {
                    exposureNumber = Integer.parseInt(tempExposureNumber);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            documentation = "https://projects.highspeed.vip/resource/covid19/schools/" + tempRecord.getId();
            tempRecord.setStatus("Imported");
            BCSchoolsCOVID19 bcSchoolsCOVID19 = new BCSchoolsCOVID19();
            bcSchoolsCOVID19.setId(tempRecord.getId());
            bcSchoolsCOVID19.setSchoolsInfo(schoolsInfo);
            bcSchoolsCOVID19.setHealthRegion(healthRegion);
            bcSchoolsCOVID19.setNotificationDate(notificationDate);
            bcSchoolsCOVID19.setNotificationMethod(notificationMethod);
            bcSchoolsCOVID19.setExposureDate(exposureDate);
            bcSchoolsCOVID19.setExposureNumber(exposureNumber);
            bcSchoolsCOVID19.setExtraInfo(extraInfo);
            bcSchoolsCOVID19.setDocumentation(documentation);
            bcSchoolsCOVID19TempRecordService.saveAndFlush(tempRecord);
            bcSchoolsCOVID19Service.saveAndFlush(bcSchoolsCOVID19);
            System.out.println(bcSchoolsCOVID19.getId());
        }
        if (i == totalRecords) {
            System.out.println("导入成功: 共" + totalRecords + "条记录!");
        } else {
            System.out.println("导入" + (i - 1) + "条记录");
            System.out.println("第" + i + "条记录: " + errorMessage);
        }
    }

}
