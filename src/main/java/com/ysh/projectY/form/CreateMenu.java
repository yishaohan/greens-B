package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;

import javax.validation.GroupSequence;

@com.ysh.projectY.form.valid.CreateMenu(groups = Second.class)
@GroupSequence({First.class, Second.class, CreateMenu.class})
public class CreateMenu extends MenuForm {
    @Override
    public String toString() {
        return "CreateMenu{" +
                "parentId=" + parentId +
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
