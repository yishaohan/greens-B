package com.ysh.projectY.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "school_district")
public class SchoolDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`district_number`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局编号'")
    private String districtNumber;

    @Column(name = "`district_name`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局名称'")
    private String districtName;

    @Column(name = "`district_phone`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局电话'")
    private String districtPhone;

    @Column(name = "`district_fax`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局传真'")
    private String districtFax;

    @Column(name = "`district_web_address`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局网站'")
    private String districtWebAddress;

    // 双向关联
    @ManyToOne(fetch = FetchType.LAZY)
    CitiesInfo citiesInfo;

    @Column(name = "`no_city`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '城市'")
    private String noCity;

    @Column(name = "`no_province`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '省份'")
    private String noProvince;

    @Column(name = "`postal_code`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局邮编'")
    private String postalCode;

    @Column(name = "`address`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '教育局地址'")
    private String address;

    @Column(name = "`courier_postal_code`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮寄地址邮政编码'")
    private String courierPostalCode;

    @Column(name = "`courier_address`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮寄地址'")
    private String courierAddress;

    // 双向关联
    @OneToMany(mappedBy = "schoolDistrict", fetch = FetchType.LAZY)
    Set<SchoolDistrictContacts> contacts;

    // 双向关联
    @OneToMany(mappedBy = "schoolDistrict", fetch = FetchType.LAZY)
    Set<SchoolsInfo> schoolsInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(String districtNumber) {
        this.districtNumber = districtNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictPhone() {
        return districtPhone;
    }

    public void setDistrictPhone(String districtPhone) {
        this.districtPhone = districtPhone;
    }

    public String getDistrictFax() {
        return districtFax;
    }

    public void setDistrictFax(String districtFax) {
        this.districtFax = districtFax;
    }

    public String getDistrictWebAddress() {
        return districtWebAddress;
    }

    public void setDistrictWebAddress(String districtWebAddress) {
        this.districtWebAddress = districtWebAddress;
    }

    public CitiesInfo getCitiesInfo() {
        return citiesInfo;
    }

    public void setCitiesInfo(CitiesInfo citiesInfo) {
        this.citiesInfo = citiesInfo;
    }

    public String getNoCity() {
        return noCity;
    }

    public void setNoCity(String noCity) {
        this.noCity = noCity;
    }

    public String getNoProvince() {
        return noProvince;
    }

    public void setNoProvince(String noProvince) {
        this.noProvince = noProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourierPostalCode() {
        return courierPostalCode;
    }

    public void setCourierPostalCode(String courierPostalCode) {
        this.courierPostalCode = courierPostalCode;
    }

    public String getCourierAddress() {
        return courierAddress;
    }

    public void setCourierAddress(String courierAddress) {
        this.courierAddress = courierAddress;
    }

    public Set<SchoolDistrictContacts> getContacts() {
        return contacts;
    }

    public void setContacts(Set<SchoolDistrictContacts> contacts) {
        this.contacts = contacts;
    }

    public Set<SchoolsInfo> getSchoolsInfo() {
        return schoolsInfo;
    }

    public void setSchoolsInfo(Set<SchoolsInfo> schoolsInfo) {
        this.schoolsInfo = schoolsInfo;
    }

    @Override
    public String toString() {
        return "SchoolDistrict{\n" +
                "id = " + id +
                ", \ndistrictNumber = '" + districtNumber + '\'' +
                ", \ndistrictName = '" + districtName + '\'' +
                ", \ndistrictPhone = '" + districtPhone + '\'' +
                ", \ndistrictFax = '" + districtFax + '\'' +
                ", \ndistrictWebAddress = '" + districtWebAddress + '\'' +
                ", \ncitiesInfo = " + citiesInfo +
                ", \nnoCity = '" + noCity + '\'' +
                ", \nnoProvince = '" + noProvince + '\'' +
                ", \npostalCode = '" + postalCode + '\'' +
                ", \naddress = '" + address + '\'' +
                ", \ncourierPostalCode = '" + courierPostalCode + '\'' +
                ", \ncourierAddress = '" + courierAddress + '\'' +
                ", \ncontacts = " + contacts.getClass().getName() +
                ", \nschoolsInfo = " + schoolsInfo.getClass().getName() +
                "\n}";
    }
}
