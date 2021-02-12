package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@com.ysh.projectY.form.valid.UpdateMenu(groups = Second.class)
@GroupSequence({First.class, Second.class, UpdateMenu.class})
public class UpdateMenu extends MenuForm {

    @NotNull(message = "project-y.valid.UpdateMenu.id.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateMenu.id.decimal-min", value = "1", groups = First.class)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdateMenu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", menuGrade=" + menuGrade +
                ", sortId=" + sortId +
                ", menuName='" + menuName + '\'' +
                ", menuDescript='" + menuDescript + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", createDateTime='" + createDateTime + '\'' +
                ", updateDateTime='" + updateDateTime + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
