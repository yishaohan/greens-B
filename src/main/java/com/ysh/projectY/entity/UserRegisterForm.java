package com.ysh.projectY.entity;

import com.ysh.projectY.valid.UserRegisterFormValidator;
import com.ysh.projectY.valid.group.First;
import com.ysh.projectY.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@UserRegisterFormValidator(groups = Second.class)
@GroupSequence({First.class, Second.class, UserRegisterForm.class})
public class UserRegisterForm {

    @NotBlank(message = "project-y.valid.user.username.not-blank", groups = First.class)
    @Email(message = "project-y.valid.user.username.email", groups = First.class)
    private String username;

    @NotBlank(message = "project-y.valid.user.mobile-phone.not-blank", groups = First.class)
    @Length(message = "project-y.valid.user.mobile-phone.length", min = 10, max = 11, groups = First.class)
    @Pattern(regexp = "^[0-9]\\d{9,11}$", message = "project-y.valid.user.mobile-phone.digits", groups = First.class)
    private String mobilePhone;

    @NotBlank(message = "project-y.valid.user.password.not-blank", groups = First.class)
    @Size(message = "project-y.valid.user.password.size", min = 6, max = 18, groups = First.class)
    private String password;

    @Length(message = "project-y.valid.user.nickname.length", min = 3, max = 18, groups = First.class)
    private String nickname;

    private String avatarURL;

    @NotBlank(message = "project-y.valid.user.sms-captcha.not-blank", groups = First.class)
    @Length(message = "project-y.valid.user.sms-captcha.length", min = 6, max = 6, groups = First.class)
    @Pattern(regexp = "^[0-9]\\d{5}$", message = "project-y.valid.user.sms-captcha.digits", groups = First.class)
    private String smsCaptcha;

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

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", smsCaptcha='" + smsCaptcha + '\'' +
                '}';
    }
}
