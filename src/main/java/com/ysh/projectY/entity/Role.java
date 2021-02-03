package com.ysh.projectY.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`role_name`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '角色名称'")
    private String roleName;

    @Column(name = "`role_descript`", columnDefinition = "varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '角色描述'")
    private String roleDescript;

    @ColumnDefault("1")
    @Column(name = "`role_enabled`", columnDefinition = "tinyint(1) NOT NULL comment '角色是否启用'")
    private boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescript() {
        return roleDescript;
    }

    public void setRoleDescript(String roleDescript) {
        this.roleDescript = roleDescript;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDescript='" + roleDescript + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
