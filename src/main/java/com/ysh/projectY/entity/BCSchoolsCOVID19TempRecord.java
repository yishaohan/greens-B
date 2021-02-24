package com.ysh.projectY.entity;

import javax.persistence.*;

@Entity(name = "_temp_bc_school_covid19")
public class BCSchoolsCOVID19TempRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", length = 32, nullable = false)
    private int id;

    @Column(name = "`notification_date`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '通知日期'")
    private String notificationDate;

    @Column(name = "`school_name`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '学校名称'")
    private String schoolName;

    @Column(name = "`school_address`", columnDefinition = "varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '学校地址'")
    private String schoolAddress;

    @Column(name = "`postcode`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮编'")
    private String postcode;

    @Column(name = "`city`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '城市'")
    private String city;

    @Column(name = "`school_district`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '学区'")
    private String schoolDistrict;

    @Column(name = "`health_region`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '卫生局'")
    private String healthRegion;

    @Column(name = "`notification_method`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '通知方式'")
    private String notificationMethod;

    @Column(name = "`exposure_date`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '感染日期'")
    private String exposureDate;

    @Column(name = "`extra_info`", columnDefinition = "varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '额外信息'")
    private String extraInfo;

    @Column(name = "`documentation`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '文档'")
    private String documentation;

    @Column(name = "`status`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '状态'")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getHealthRegion() {
        return healthRegion;
    }

    public void setHealthRegion(String healthRegion) {
        this.healthRegion = healthRegion;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BCSchoolCOVID19{" +
                "id=" + id +
                ", notificationDate='" + notificationDate + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", schoolDistrict='" + schoolDistrict + '\'' +
                ", healthRegion='" + healthRegion + '\'' +
                ", notificationMethod='" + notificationMethod + '\'' +
                ", exposureDate='" + exposureDate + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", documentation='" + documentation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
