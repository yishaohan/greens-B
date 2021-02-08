package com.ysh.projectY.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "authorization")
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`parent_id`", columnDefinition = "int NOT NULL comment '父权限ID'")
    private int parentId;

    @Column(name = "`auth_grade`", columnDefinition = "int NOT NULL comment '权限等级'")
    private int authGrade;

    @Column(name = "`auth_name`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '权限名称'")
    private String authName;

    @Column(name = "`auth_descript`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '权限描述'")
    private String authDescript;

    @Column(name = "`request_url`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '资源URL'")
    private String requestUrl;

    @Column(name = "`request_methods`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '资源支持的方法'")
    private String requestMethod;

    // @CreatedDate
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "`create_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '创建时间'")
    private Timestamp createDateTime;

    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "`update_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '更新时间'")
    private Timestamp updateDateTime;

    @ColumnDefault("1")
    @Column(name = "`auth_enabled`", columnDefinition = "tinyint(1) NOT NULL comment '权限是否启用'")
    private boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAuthGrade() {
        return authGrade;
    }

    public void setAuthGrade(int authGrade) {
        this.authGrade = authGrade;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDescript() {
        return authDescript;
    }

    public void setAuthDescript(String authDescript) {
        this.authDescript = authDescript;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", authGrade=" + authGrade +
                ", authName='" + authName + '\'' +
                ", authDescript='" + authDescript + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                ", enabled=" + enabled +
                '}';
    }
}
