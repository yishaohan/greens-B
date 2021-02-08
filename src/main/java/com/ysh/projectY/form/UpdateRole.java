package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@com.ysh.projectY.form.valid.UpdateRole(groups = Second.class)
@GroupSequence({First.class, Second.class, UpdateRole.class})
public class UpdateRole {

    @NotNull(message = "project-y.valid.UpdateRole.id.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateRole.id.decimal-min", value = "1", groups = First.class)
    private Integer id;

    @NotBlank(message = "project-y.valid.UpdateRole.roleName.not-blank", groups = First.class)
    @Length(message = "project-y.valid.UpdateRole.roleName.length", min = 6, max = 18, groups = First.class)
    private String roleName;

    //    @NotBlank(message = "project-y.valid.UpdateRole.roleDescript.not-blank", groups = First.class)
    private String roleDescript;

    // @NotNull(message = "project-y.valid.UpdateRole.enabled.not-null", groups = First.class)
    private Boolean enabled;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UpdateRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDescript='" + roleDescript + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
