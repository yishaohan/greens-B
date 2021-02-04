package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@com.ysh.projectY.form.valid.DeleteUserRole(groups = Second.class)
@GroupSequence({First.class, Second.class, DeleteUserRole.class})
public class DeleteUserRole {

    @NotNull(message = "project-y.valid.DeleteUserRole.userID.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.DeleteUserRole.userID.decimal-min", value = "1", groups = First.class)
    private int userID;

    @NotNull(message = "project-y.valid.DeleteUserRole.roleID.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.DeleteUserRole.roleID.decimal-min", value = "1", groups = First.class)
    private int roleID;

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
        return "DeleteUserRole{" +
                "userID=" + userID +
                ", roleID=" + roleID +
                '}';
    }
}
