package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;
import com.ysh.projectY.form.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@com.ysh.projectY.form.valid.CreateUser(groups = Second.class)
@GroupSequence({First.class, Second.class, CreateUser.class})
public class CreateUser {

    @NotBlank(message = "project-y.valid.CreateUser.username.not-blank", groups = First.class)
    @Email(message = "project-y.valid.CreateUser.username.email", groups = First.class)
    private String username;

    @NotBlank(message = "project-y.valid.CreateUser.mobile-phone.not-blank", groups = First.class)
    @Length(message = "project-y.valid.CreateUser.mobile-phone.length", min = 10, max = 11, groups = First.class)
    @Pattern(regexp = "^[0-9]\\d{9,11}$", message = "project-y.valid.CreateUser.mobile-phone.digits", groups = First.class)
    private String mobilePhone;

    @NotBlank(message = "project-y.valid.CreateUser.password.not-blank", groups = First.class)
    @Size(message = "project-y.valid.CreateUser.password.size", min = 6, max = 18, groups = First.class)
    private String password;

    @Length(message = "project-y.valid.RegisterUser.nickname.length", min = 3, max = 18, groups = First.class)
    private String nickname;

    private String avatarURL;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                '}';
    }
}
