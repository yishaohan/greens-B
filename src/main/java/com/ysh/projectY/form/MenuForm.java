package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MenuForm {

    @NotNull(message = "project-y.valid.CreateMenu.parentId.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.CreateMenu.parentId.decimal-min", value = "0", groups = First.class)
    Integer parentId;

    @NotNull(message = "project-y.valid.CreateMenu.menuGrade.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.CreateMenu.menuGrade.decimal-min", value = "1", groups = First.class)
    Integer menuGrade;

    @NotNull(message = "project-y.valid.CreateMenu.sortId.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.CreateMenu.sortId.decimal-min", value = "1", groups = First.class)
    Integer sortId;

    @NotBlank(message = "project-y.valid.CreateMenu.menuIcon.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateMenu.menuIcon.length", min = 1, max = 64, groups = First.class)
    String menuIcon;

    @NotBlank(message = "project-y.valid.CreateMenu.menuName.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateMenu.menuName.length", min = 1, max = 20, groups = First.class)
    String menuName;

    @NotBlank(message = "project-y.valid.CreateMenu.menuDescript.not-blank", groups = First.class)
    String menuDescript;

    @NotBlank(message = "project-y.valid.CreateMenu.menuPath.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateMenu.menuPath.length", min = 1, max = 128, groups = First.class)
    String menuPath;

    @NotBlank(message = "project-y.valid.CreateMenu.menuComponent.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateMenu.menuComponent.length", min = 1, max = 128, groups = First.class)
    String menuComponent;

    // @NotNull(message = "project-y.valid.CreateMenu.createDateTime.not-null", groups = First.class)
    String createDateTime;

    // @NotNull(message = "project-y.valid.CreateMenu.updateDateTime.not-null", groups = First.class)
    String updateDateTime;

    // @NotNull(message = "project-y.valid.CreateMenu.enabled.not-null", groups = First.class)
    Boolean enabled;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(Integer menuGrade) {
        this.menuGrade = menuGrade;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
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

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "MenuForm{" +
                "parentId=" + parentId +
                ", menuGrade=" + menuGrade +
                ", sortId=" + sortId +
                ", menuIcon='" + menuIcon + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuDescript='" + menuDescript + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuComponent='" + menuComponent + '\'' +
                ", createDateTime='" + createDateTime + '\'' +
                ", updateDateTime='" + updateDateTime + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
