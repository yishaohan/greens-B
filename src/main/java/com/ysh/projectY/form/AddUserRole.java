package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@com.ysh.projectY.form.valid.AddUserRole(groups = Second.class)
@GroupSequence({First.class, Second.class, AddUserRole.class})
public class AddUserRole {

    @NotNull(message = "project-y.valid.AddUserRole.userID.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.AddUserRole.userID.decimal-min", value = "1", groups = First.class)
    private Integer userID;

    @NotNull(message = "project-y.valid.AddUserRole.roleID.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.AddUserRole.roleID.decimal-min", value = "1", groups = First.class)
    private Integer roleID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "AddUserRole{" +
                "userID=" + userID +
                ", roleID=" + roleID +
                '}';
    }
}
