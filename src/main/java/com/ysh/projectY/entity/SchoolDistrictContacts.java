package com.ysh.projectY.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "school_district_contacts")
public class SchoolDistrictContacts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    // 双向关联
    @ManyToOne(fetch = FetchType.EAGER)
    SchoolDistrict schoolDistrict;

    @Column(name = "`contact_type`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '联系人类型'")
    private String contactType;

    @Column(name = "`contact_title`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '联系人称谓'")
    private String contactTitle;

    @Column(name = "`contact_first_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '联系人名字'")
    private String contactFirstName;

    @Column(name = "`contact_last_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '联系人姓'")
    private String contactLastName;

    @Column(name = "`position_title`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '职位名称'")
    private String positionTitle;

    @Column(name = "`contact_phone`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '联系电话'")
    private String contactPhone;

    @Column(name = "`contact_phone_extension`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '分机号码'")
    private String contactPhoneExtension;

    @Column(name = "`contact_fax`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '传真'")
    private String contactFax;

    @Column(name = "`contact_email`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '电子邮件'")
    private String contactEmail;

    @Column(name = "`no_mailing_address`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮寄地址'")
    private String noMailingAddress;

    @Column(name = "`no_mailing_city`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮寄城市'")
    private String noMailingCity;

    @Column(name = "`no_mailing_province`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮寄省份'")
    private String noMailingProvince;

    @Column(name = "`no_mailing_postal_code`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮政编码'")
    private String noMailingPostalCode;

    @Column(name = "`no_courier_address`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '快递地址'")
    private String noCourierAddress;

    @Column(name = "`no_courier_postal_code`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '快递邮政编码'")
    private String noCourierPostalCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SchoolDistrict getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(SchoolDistrict schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhoneExtension() {
        return contactPhoneExtension;
    }

    public void setContactPhoneExtension(String contactPhoneExtension) {
        this.contactPhoneExtension = contactPhoneExtension;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getNoMailingAddress() {
        return noMailingAddress;
    }

    public void setNoMailingAddress(String noMailingAddress) {
        this.noMailingAddress = noMailingAddress;
    }

    public String getNoMailingCity() {
        return noMailingCity;
    }

    public void setNoMailingCity(String noMailingCity) {
        this.noMailingCity = noMailingCity;
    }

    public String getNoMailingProvince() {
        return noMailingProvince;
    }

    public void setNoMailingProvince(String noMailingProvince) {
        this.noMailingProvince = noMailingProvince;
    }

    public String getNoMailingPostalCode() {
        return noMailingPostalCode;
    }

    public void setNoMailingPostalCode(String noMailingPostalCode) {
        this.noMailingPostalCode = noMailingPostalCode;
    }

    public String getNoCourierAddress() {
        return noCourierAddress;
    }

    public void setNoCourierAddress(String noCourierAddress) {
        this.noCourierAddress = noCourierAddress;
    }

    public String getNoCourierPostalCode() {
        return noCourierPostalCode;
    }

    public void setNoCourierPostalCode(String noCourierPostalCode) {
        this.noCourierPostalCode = noCourierPostalCode;
    }

//    @Override
//    public String toString() {
//        return "SchoolDistrictContacts{\n" +
//                "id = " + id +
//                ", \nschoolDistrict = " + schoolDistrict +
//                ", \ncontactType = '" + contactType + '\'' +
//                ", \ncontactTitle = '" + contactTitle + '\'' +
//                ", \ncontactFirstName = '" + contactFirstName + '\'' +
//                ", \ncontactLastName = '" + contactLastName + '\'' +
//                ", \npositionTitle = '" + positionTitle + '\'' +
//                ", \ncontactPhone = '" + contactPhone + '\'' +
//                ", \ncontactPhoneExtension = '" + contactPhoneExtension + '\'' +
//                ", \ncontactFax = '" + contactFax + '\'' +
//                ", \ncontactEmail = '" + contactEmail + '\'' +
//                ", \nnoMailingAddress = '" + noMailingAddress + '\'' +
//                ", \nnoMailingCity = '" + noMailingCity + '\'' +
//                ", \nnoMailingProvince = '" + noMailingProvince + '\'' +
//                ", \nnoMailingPostalCode = '" + noMailingPostalCode + '\'' +
//                ", \nnoCourierAddress = '" + noCourierAddress + '\'' +
//                ", \nnoCourierPostalCode = '" + noCourierPostalCode + '\'' +
//                "\n}";
//    }
}
