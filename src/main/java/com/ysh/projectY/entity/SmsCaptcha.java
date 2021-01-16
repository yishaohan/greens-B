package com.ysh.projectY.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "sms_captcha")
public class SmsCaptcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`mobile_phone`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '手机号码'")
    private String mobilePhone;

    @Column(name = "`captcha`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '短信验证码'")
    private String captcha;

    @Column(name = "`create_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '创建时间'")
    private Timestamp createDateTime;

    @Column(name = "`source`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '来源'")
    private String source;

    @Column(name = "`client_ip`", columnDefinition = "varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '客户端IP地址'")
    private String clientIp;

    @Column(name = "`status`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '状态'")
    private String status;

    @Column(name = "`update_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '更新时间'")
    private Timestamp updateDateTime;

    @Column(name = "`verified_times`", columnDefinition = "int DEFAULT 0 NOT NULL comment '验证次数'")
    private int verifiedTimes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public int getVerifiedTimes() {
        return verifiedTimes;
    }

    public void setVerifiedTimes(int verifiedTimes) {
        this.verifiedTimes = verifiedTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmsCaptcha that = (SmsCaptcha) o;
        return mobilePhone.equals(that.mobilePhone) && captcha.equals(that.captcha) && createDateTime.equals(that.createDateTime) && source.equals(that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobilePhone, captcha, createDateTime, source);
    }

    @Override
    public String toString() {
        return "SmsCaptcha{" +
                "id=" + id +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", captcha='" + captcha + '\'' +
                ", createDateTime=" + createDateTime +
                ", source='" + source + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", status='" + status + '\'' +
                ", updateDateTime=" + updateDateTime +
                ", verifiedTimes=" + verifiedTimes +
                '}';
    }
}
