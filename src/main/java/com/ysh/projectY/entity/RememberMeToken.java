package com.ysh.projectY.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

//@DynamicUpdate
//@DynamicInsert
@Entity(name = "remember_me_token")
public class RememberMeToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`username`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '用户名称(Email)'")
    private String username;

    @Column(name = "`series`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment 'series'")
    private String series;

    @Column(name = "`token`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment 'token'")
    private String token;

    @Column(name = "`last_used_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '最后使用时间'")
    private Timestamp lastUsedDateTime;

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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getLastUsedDateTime() {
        return lastUsedDateTime;
    }

    public void setLastUsedDateTime(Timestamp lastUsedDateTime) {
        this.lastUsedDateTime = lastUsedDateTime;
    }

    @Override
    public String toString() {
        return "RememberMeToken{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", series='" + series + '\'' +
                ", token='" + token + '\'' +
                ", lastUsedDateTime=" + lastUsedDateTime +
                '}';
    }
}
