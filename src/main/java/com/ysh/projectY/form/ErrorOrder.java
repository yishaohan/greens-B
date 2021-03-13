package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ErrorOrder {

    @NotBlank(message = "project-y.valid.CreateOrder.error.not-blank", groups = First.class)
    private String error;

    @NotBlank(message = "project-y.valid.CreateOrder.name.not-blank", groups = First.class)
    private String name;

    @NotBlank(message = "project-y.valid.CreateOrder.telePhone.not-blank", groups = First.class)
    private String telePhone;

    @NotBlank(message = "project-y.valid.CreateOrder.email.not-blank", groups = First.class)
    private String email;

    @NotBlank(message = "project-y.valid.CreateOrder.postcode.not-blank", groups = First.class)
    private String postcode;

    @NotBlank(message = "project-y.valid.CreateOrder.address.not-blank", groups = First.class)
    private String address;

    @NotBlank(message = "project-y.valid.CreateOrder.remark.not-blank", groups = First.class)
    private String remark;

    @NotNull(message = "project-y.valid.CreateOrder.quantity.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.CreateOrder.quantity.decimal-min", value = "1", groups = First.class)
    private Integer quantity;

    @NotNull(message = "project-y.valid.CreateOrder.item1.not-null", groups = First.class)
    private Boolean item1;

    @NotNull(message = "project-y.valid.CreateOrder.item2.not-null", groups = First.class)
    private Boolean item2;

    @NotNull(message = "project-y.valid.CreateOrder.item3.not-null", groups = First.class)
    private Boolean item3;

    @NotNull(message = "project-y.valid.CreateOrder.item4.not-null", groups = First.class)
    private Boolean item4;

    @NotNull(message = "project-y.valid.CreateOrder.item5.not-null", groups = First.class)
    private Boolean item5;

    @NotNull(message = "project-y.valid.CreateOrder.item6.not-null", groups = First.class)
    private Boolean item6;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getItem1() {
        return item1;
    }

    public void setItem1(Boolean item1) {
        this.item1 = item1;
    }

    public Boolean getItem2() {
        return item2;
    }

    public void setItem2(Boolean item2) {
        this.item2 = item2;
    }

    public Boolean getItem3() {
        return item3;
    }

    public void setItem3(Boolean item3) {
        this.item3 = item3;
    }

    public Boolean getItem4() {
        return item4;
    }

    public void setItem4(Boolean item4) {
        this.item4 = item4;
    }

    public Boolean getItem5() {
        return item5;
    }

    public void setItem5(Boolean item5) {
        this.item5 = item5;
    }

    public Boolean getItem6() {
        return item6;
    }

    public void setItem6(Boolean item6) {
        this.item6 = item6;
    }

    @Override
    public String toString() {
        return "ErrorOrder{" +
                "error='" + error + '\'' +
                ", name='" + name + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", email='" + email + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", quantity=" + quantity +
                ", item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                ", item4=" + item4 +
                ", item5=" + item5 +
                ", item6=" + item6 +
                '}';
    }
}
