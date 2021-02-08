package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@com.ysh.projectY.form.valid.CreateAuth(groups = Second.class)
@GroupSequence({First.class, Second.class, CreateAuth.class})
public class CreateAuth {

    @NotNull(message = "project-y.valid.CreateAuth.parentId.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.CreateAuth.parentId.decimal-min", value = "0", groups = First.class)
    private Integer parentId;

    @NotNull(message = "project-y.valid.CreateAuth.authGrade.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.CreateAuth.authGrade.decimal-min", value = "1", groups = First.class)
    private Integer authGrade;

    @NotBlank(message = "project-y.valid.CreateAuth.authName.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateAuth.authName.length", min = 2, max = 20, groups = First.class)
    private String authName;

    @NotBlank(message = "project-y.valid.CreateAuth.authDescript.not-blank", groups = First.class)
    private String authDescript;

    @NotBlank(message = "project-y.valid.CreateAuth.requestUrl.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateAuth.requestUrl.length", min = 1, max = 200, groups = First.class)
    private String requestUrl;

    // @NotBlank(message = "project-y.valid.CreateAuth.requestMethod.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateAuth.requestMethod.length", min = 1, max = 8, groups = First.class)
    private String requestMethod;

    // @NotNull(message = "project-y.valid.CreateAuth.createDateTime.not-null", groups = First.class)
    private String createDateTime;

    // @NotNull(message = "project-y.valid.CreateAuth.updateDateTime.not-null", groups = First.class)
    private String updateDateTime;

    // @NotNull(message = "project-y.valid.CreateAuth.enabled.not-null", groups = First.class)
    private Boolean enabled;

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
