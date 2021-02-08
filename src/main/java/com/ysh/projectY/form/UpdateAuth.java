package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@com.ysh.projectY.form.valid.UpdateAuth(groups = Second.class)
@GroupSequence({First.class, Second.class, UpdateAuth.class})
public class UpdateAuth {

    @NotNull(message = "project-y.valid.UpdateAuth.id.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateAuth.id.decimal-min", value = "1", groups = First.class)
    private Integer id;

    @NotNull(message = "project-y.valid.UpdateAuth.parentId.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateAuth.parentId.decimal-min", value = "0", groups = First.class)
    private Integer parentId;

    @NotNull(message = "project-y.valid.UpdateAuth.authGrade.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateAuth.authGrade.decimal-min", value = "1", groups = First.class)
    private Integer authGrade;

    @NotBlank(message = "project-y.valid.UpdateAuth.authName.not-blank", groups = First.class)
    @Length(message = "project-y.valid.UpdateAuth.authName.length", min = 2, max = 20, groups = First.class)
    private String authName;

    @NotBlank(message = "project-y.valid.UpdateAuth.authDescript.not-blank", groups = First.class)
    private String authDescript;

    @NotBlank(message = "project-y.valid.UpdateAuth.requestUrl.not-blank", groups = First.class)
    @Length(message = "project-y.valid.UpdateAuth.requestUrl.length", min = 1, max = 200, groups = First.class)
    private String requestUrl;

    // @NotBlank(message = "project-y.valid.UpdateAuth.requestMethod.not-blank", groups = First.class)
    @Length(message = "project-y.valid.UpdateAuth.requestMethod.length", min = 1, max = 8, groups = First.class)
    private String requestMethod;

    // @NotNull(message = "project-y.valid.UpdateAuth.createDateTime.not-null", groups = First.class)
    private String createDateTime;

    // @NotNull(message = "project-y.valid.UpdateAuth.updateDateTime.not-null", groups = First.class)
    private String updateDateTime;

    // @NotNull(message = "project-y.valid.UpdateAuth.enabled.not-null", groups = First.class)
    private Boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAuthGrade() {
        return authGrade;
    }

    public void setAuthGrade(int authGrade) {
        this.authGrade = authGrade;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDescript() {
        return authDescript;
    }

    public void setAuthDescript(String authDescript) {
        this.authDescript = authDescript;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
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
        return "UpdateAuth{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", authGrade=" + authGrade +
                ", authName='" + authName + '\'' +
                ", authDescript='" + authDescript + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", createDateTime='" + createDateTime + '\'' +
                ", updateDateTime='" + updateDateTime + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
