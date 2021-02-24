package com.ysh.projectY.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "bc_school_covid19")
public class BCSchoolsCOVID19 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", length = 32, nullable = false)
    private int id;

    // 双向关联
    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolsInfo schoolsInfo;

    // 双向关联
    @ManyToOne(fetch = FetchType.LAZY)
    private HealthRegion healthRegion;

    @Column(name = "`notification_date`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '通知日期'")
    private Timestamp notificationDate;

    @Column(name = "`notification_method`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '通知方式'")
    private String notificationMethod;

    @Column(name = "`exposure_date`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '感染日期'")
    private String exposureDate;

    @Column(name = "`exposure_number`", columnDefinition = "varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '额外信息'")
    private int exposureNumber;

    @Column(name = "`extra_info`", columnDefinition = "varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '额外信息'")
    private String extraInfo;

    @Column(name = "`documentation`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '文档'")
    private String documentation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SchoolsInfo getSchoolsInfo() {
        return schoolsInfo;
    }

    public void setSchoolsInfo(SchoolsInfo schoolsInfo) {
        this.schoolsInfo = schoolsInfo;
    }

    public HealthRegion getHealthRegion() {
        return healthRegion;
    }

    public void setHealthRegion(HealthRegion healthRegion) {
        this.healthRegion = healthRegion;
    }

    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public String getExposureDate() {
        return exposureDate;
    }

    public void setExposureDate(String exposureDate) {
        this.exposureDate = exposureDate;
    }

    public int getExposureNumber() {
        return exposureNumber;
    }

    public void setExposureNumber(int exposureNumber) {
        this.exposureNumber = exposureNumber;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    @Override
    public String toString() {
        return "BCSchoolsCOVID19{\n" +
                "id = " + id +
                ", \nschoolsInfo = " + schoolsInfo +
                ", \nhealthRegion = " + healthRegion +
                ", \nnotificationDate = " + notificationDate +
                ", \nnotificationMethod = '" + notificationMethod + '\'' +
                ", \nexposureDate = '" + exposureDate + '\'' +
                ", \nexposureNumber = " + exposureNumber +
                ", \nextraInfo = '" + extraInfo + '\'' +
                ", \ndocumentation = '" + documentation + '\'' +
                "\n}";
    }
}
