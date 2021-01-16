package com.ysh.projectY.entity;

import com.ysh.projectY.valid.SmsCaptchaFormValidator;
import com.ysh.projectY.valid.group.First;
import com.ysh.projectY.valid.group.Second;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@SmsCaptchaFormValidator(groups = Second.class)
@GroupSequence({First.class, Second.class, SmsCaptchaForm.class})
public class SmsCaptchaForm {

    @NotBlank(message = "project-y.valid.sms-captcha.mobile-phone.not-blank", groups = First.class)
    @Length(message = "project-y.valid.sms-captcha.mobile-phone.length", min = 10, max = 11, groups = First.class)
    @Pattern(regexp = "^[0-9]\\d{9,11}$", message = "project-y.valid.sms-captcha.mobile-phone.digits", groups = First.class)
    private String mobilePhone;

    @NotBlank(message = "project-y.valid.sms-captcha.source.not-blank", groups = First.class)
    private String source;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "SmsCaptchaForm{" +
                "mobilePhone='" + mobilePhone + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
