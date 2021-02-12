package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@com.ysh.projectY.form.valid.UpdateRoleMenus(groups = Second.class)
@GroupSequence({First.class, Second.class, UpdateRoleMenus.class})
public class UpdateRoleMenus {

    @NotNull(message = "project-y.valid.UpdateRoleMenus.roleId.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateRoleMenus.roleId.decimal-min", value = "1", groups = First.class)
    private Integer roleId;

    @NotNull(message = "project-y.valid.UpdateRoleMenus.addMenuIds.not-null", groups = First.class)
    private List<Integer> addMenuIds;

    @NotNull(message = "project-y.valid.UpdateRoleMenus.removeMenuIds.not-null", groups = First.class)
    private List<Integer> removeMenuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getAddMenuIds() {
        return addMenuIds;
    }

    public void setAddMenuIds(List<Integer> addMenuIds) {
        this.addMenuIds = addMenuIds;
    }

    public List<Integer> getRemoveMenuIds() {
        return removeMenuIds;
    }

    public void setRemoveMenuIds(List<Integer> removeMenuIds) {
        this.removeMenuIds = removeMenuIds;
    }

    @Override
    public String toString() {
        return "UpdateRoleMenus{" +
                "roleId=" + roleId +
                ", addMenuIds=" + addMenuIds +
                ", removeMenuIds=" + removeMenuIds +
                '}';
    }
}
