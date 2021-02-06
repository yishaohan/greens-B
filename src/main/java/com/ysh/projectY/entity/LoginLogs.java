package com.ysh.projectY.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "login_logs")
public class LoginLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`username`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '用户名称(Email)'")
    private String username;

    @Column(name = "`mobile_phone`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '手机号码'")
    private String mobilePhone;

    @Column(name = "`password`", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '密码'")
    private String password;

    @Column(name = "`login_url`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '来源'")
    private String loginURL;

    @Column(name = "`session_id`", columnDefinition = "varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '会话ID'")
    private String sessionID;

    @Column(name = "`client_ip`", columnDefinition = "varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '客户端IP地址'")
    private String clientIP;

    @Column(name = "`status`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '状态'")
    private String status;

    // @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "`login_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '登录时间'")
    private Timestamp loginDateTime;

    // @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "`logout_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP comment '登出时间'")
    private Timestamp logoutDateTime;

    @Column(name = "`remarks`", columnDefinition = "varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '备注'")
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginURL() {
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(Timestamp loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public Timestamp getLogoutDateTime() {
        return logoutDateTime;
    }

    public void setLogoutDateTime(Timestamp logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "LoginLogs{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", loginURL='" + loginURL + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", clientIP='" + clientIP + '\'' +
                ", status='" + status + '\'' +
                ", loginDateTime=" + loginDateTime +
                ", logoutDateTime=" + logoutDateTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
