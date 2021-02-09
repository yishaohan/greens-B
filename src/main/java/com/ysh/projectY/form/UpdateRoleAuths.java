package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@com.ysh.projectY.form.valid.UpdateRoleAuths(groups = Second.class)
@GroupSequence({First.class, Second.class, UpdateRoleAuths.class})
public class UpdateRoleAuths {

    @NotNull(message = "project-y.valid.UpdateRoleAuths.roleId.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateRoleAuths.roleId.decimal-min", value = "1", groups = First.class)
    private Integer roleId;

    @NotNull(message = "project-y.valid.UpdateRoleAuths.addAuthIds.not-null", groups = First.class)
    private List<Integer> addAuthIds;

    @NotNull(message = "project-y.valid.UpdateRoleAuths.removeAuthIds.not-null", groups = First.class)
    private List<Integer> removeAuthIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getAddAuthIds() {
        return addAuthIds;
    }

    public void setAddAuthIds(List<Integer> addAuthIds) {
        this.addAuthIds = addAuthIds;
    }

    public List<Integer> getRemoveAuthIds() {
        return removeAuthIds;
    }

    public void setRemoveAuthIds(List<Integer> removeAuthIds) {
        this.removeAuthIds = removeAuthIds;
    }

    @Override
    public String toString() {
        return "UpdateRoleAuths{" +
                "roleId=" + roleId +
                ", addAuthIds=" + addAuthIds +
                ", removeAuthIds=" + removeAuthIds +
                '}';
    }
}
