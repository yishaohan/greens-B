package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@com.ysh.projectY.form.valid.CreateRole(groups = Second.class)
@GroupSequence({First.class, Second.class, CreateRole.class})
public class CreateRole {

    @NotBlank(message = "project-y.valid.CreateRole.roleName.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateRole.roleName.length", min = 6, max = 18, groups = First.class)
    private String roleName;

    //    @NotBlank(message = "project-y.valid.CreateRole.roleDescript.not-blank", groups = First.class)
    private String roleDescript;


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

    @Override
    public String toString() {
        return "CreateRole{" +
                "roleName='" + roleName + '\'' +
                ", roleDescript='" + roleDescript + '\'' +
                '}';
    }
}
