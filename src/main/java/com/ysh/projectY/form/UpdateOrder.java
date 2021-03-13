package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class UpdateOrder {

    @NotNull(message = "project-y.valid.UpdateOrder.id.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateOrder.id.decimal-min", value = "1", groups = First.class)
    private Integer id;

    @NotNull(message = "project-y.valid.UpdateOrder.status.not-null", groups = First.class)
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateOrder{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
