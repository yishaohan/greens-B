package com.ysh.projectY.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`parent_id`", columnDefinition = "int NOT NULL comment '父菜单ID'")
    private int parentId;

    @Column(name = "`menu_grade`", columnDefinition = "int NOT NULL comment '菜单等级'")
    private int menuGrade;

    @Column(name = "`sort_id`", columnDefinition = "int NOT NULL comment '父菜单ID'")
    private int sortId;

    @Column(name = "`menu_icon`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '菜单图标'")
    private String menuIcon;

    @Column(name = "`menu_name`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '菜单名称'")
    private String menuName;

    @Column(name = "`menu_descript`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '菜单描述'")
    private String menuDescript;

    @Column(name = "`menu_path`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '菜单路径'")
    private String menuPath;

    @Column(name = "`menu_component`", columnDefinition = "varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '菜单路径'")
    private String menuComponent;

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
    @Column(name = "`menu_enabled`", columnDefinition = "tinyint(1) NOT NULL comment '菜单是否启用'")
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

    public int getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(int menuGrade) {
        this.menuGrade = menuGrade;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescript() {
        return menuDescript;
    }

    public void setMenuDescript(String menuDescript) {
        this.menuDescript = menuDescript;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuComponent() {
        return menuComponent;
    }

    public void setMenuComponent(String menuComponent) {
        this.menuComponent = menuComponent;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return id == menu.id && menuName.equals(menu.menuName) && menuPath.equals(menu.menuPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuName, menuPath);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", menuGrade=" + menuGrade +
                ", sortId=" + sortId +
                ", menuIcon='" + menuIcon + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuDescript='" + menuDescript + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuComponent='" + menuComponent + '\'' +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                ", enabled=" + enabled +
                '}';
    }
}
