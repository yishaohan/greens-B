package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@com.ysh.projectY.form.valid.UpdateUser(groups = Second.class)
@GroupSequence({First.class, Second.class, UpdateUser.class})
public class UpdateUser {

    @NotNull(message = "project-y.valid.UpdateUser.id.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.UpdateUser.id.decimal-min", value = "1", groups = First.class)
    private int id;

    //    @Length(message = "project-y.valid.UpdateUser.nickname.length", min = 3, max = 18, groups = First.class)
    private String nickname;

    @NotBlank(message = "project-y.valid.UpdateUser.username.not-blank", groups = First.class)
    @Email(message = "project-y.valid.UpdateUser.username.email", groups = First.class)
    private String username;

    @NotBlank(message = "project-y.valid.UpdateUser.mobile-phone.not-blank", groups = First.class)
    @Length(message = "project-y.valid.UpdateUser.mobile-phone.length", min = 10, max = 11, groups = First.class)
    @Pattern(regexp = "^[0-9]\\d{9,11}$", message = "project-y.valid.UpdateUser.mobile-phone.digits", groups = First.class)
    private String mobilePhone;

    // 目前不检测密码的长度, 统一设置为770519
    @Size(message = "project-y.valid.UpdateUser.password.size", min = 6, max = 128, groups = First.class)
    private String password;

    @Length(message = "project-y.valid.UpdateUser.avatarURL.length", min = 3, max = 128, groups = First.class)
    private String avatarURL;

    // @NotNull(message = "project-y.valid.UpdateUser.create-date-time.not-null", groups = First.class)
    private String createDateTime;

    // @NotNull(message = "project-y.valid.UpdateUser.enabled.not-null", groups = First.class)
    private Boolean enabled;

    // @NotNull(message = "project-y.valid.UpdateUser.locked.not-null", groups = First.class)
    private Boolean locked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "UserUpdate{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", createDateTime=" + createDateTime +
                ", enabled=" + enabled +
                ", locked=" + locked +
                '}';
    }
}
