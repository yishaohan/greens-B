package com.ysh.projectY.form;

import com.ysh.projectY.form.valid.group.First;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ErrorOrder {

    @NotBlank(message = "project-y.valid.ErrorOrder.error.not-blank", groups = First.class)
    private String error;

    @NotBlank(message = "project-y.valid.ErrorOrder.name.not-blank", groups = First.class)
    private String name;

    @NotBlank(message = "project-y.valid.ErrorOrder.telePhone.not-blank", groups = First.class)
    private String telePhone;

    @NotBlank(message = "project-y.valid.ErrorOrder.email.not-blank", groups = First.class)
    private String email;

    @NotBlank(message = "project-y.valid.ErrorOrder.postcode.not-blank", groups = First.class)
    private String postcode;

    @NotBlank(message = "project-y.valid.ErrorOrder.address.not-blank", groups = First.class)
    private String address;

    @NotBlank(message = "project-y.valid.ErrorOrder.remark.not-blank", groups = First.class)
    private String remark;

    @NotNull(message = "project-y.valid.ErrorOrder.quantity.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.ErrorOrder.quantity.decimal-min", value = "1", groups = First.class)
    private Integer quantity1;

    @NotNull(message = "project-y.valid.ErrorOrder.quantity1.not-null", groups = First.class)
    @DecimalMin(message = "project-y.valid.ErrorOrder.quantity2.decimal-min", value = "1", groups = First.class)
    private Integer quantity2;

    @NotNull(message = "project-y.valid.ErrorOrder.item1_1.not-null", groups = First.class)
    private Integer item1_1;

    @NotNull(message = "project-y.valid.ErrorOrder.item1_2.not-null", groups = First.class)
    private Integer item1_2;

    @NotNull(message = "project-y.valid.ErrorOrder.item1_3.not-null", groups = First.class)
    private Integer item1_3;

    @NotNull(message = "project-y.valid.ErrorOrder.item1_4.not-null", groups = First.class)
    private Integer item1_4;

    @NotNull(message = "project-y.valid.ErrorOrder.item1_5.not-null", groups = First.class)
    private Integer item1_5;

    @NotNull(message = "project-y.valid.ErrorOrder.item1_6.not-null", groups = First.class)
    private Integer item1_6;

    @NotNull(message = "project-y.valid.ErrorOrder.item2_1.not-null", groups = First.class)
    private Integer item2_1;

    @NotNull(message = "project-y.valid.ErrorOrder.item2_2.not-null", groups = First.class)
    private Integer item2_2;

    @NotNull(message = "project-y.valid.ErrorOrder.item2_3.not-null", groups = First.class)
    private Integer item2_3;

    @NotNull(message = "project-y.valid.ErrorOrder.item2_4.not-null", groups = First.class)
    private Integer item2_4;

    @NotNull(message = "project-y.valid.ErrorOrder.item2_5.not-null", groups = First.class)
    private Integer item2_5;

    @NotNull(message = "project-y.valid.ErrorOrder.item2_6.not-null", groups = First.class)
    private Integer item2_6;

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

    public Integer getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(Integer quantity1) {
        this.quantity1 = quantity1;
    }

    public Integer getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(Integer quantity2) {
        this.quantity2 = quantity2;
    }

    public Integer getItem1_1() {
        return item1_1;
    }

    public void setItem1_1(Integer item1_1) {
        this.item1_1 = item1_1;
    }

    public Integer getItem1_2() {
        return item1_2;
    }

    public void setItem1_2(Integer item1_2) {
        this.item1_2 = item1_2;
    }

    public Integer getItem1_3() {
        return item1_3;
    }

    public void setItem1_3(Integer item1_3) {
        this.item1_3 = item1_3;
    }

    public Integer getItem1_4() {
        return item1_4;
    }

    public void setItem1_4(Integer item1_4) {
        this.item1_4 = item1_4;
    }

    public Integer getItem1_5() {
        return item1_5;
    }

    public void setItem1_5(Integer item1_5) {
        this.item1_5 = item1_5;
    }

    public Integer getItem1_6() {
        return item1_6;
    }

    public void setItem1_6(Integer item1_6) {
        this.item1_6 = item1_6;
    }

    public Integer getItem2_1() {
        return item2_1;
    }

    public void setItem2_1(Integer item2_1) {
        this.item2_1 = item2_1;
    }

    public Integer getItem2_2() {
        return item2_2;
    }

    public void setItem2_2(Integer item2_2) {
        this.item2_2 = item2_2;
    }

    public Integer getItem2_3() {
        return item2_3;
    }

    public void setItem2_3(Integer item2_3) {
        this.item2_3 = item2_3;
    }

    public Integer getItem2_4() {
        return item2_4;
    }

    public void setItem2_4(Integer item2_4) {
        this.item2_4 = item2_4;
    }

    public Integer getItem2_5() {
        return item2_5;
    }

    public void setItem2_5(Integer item2_5) {
        this.item2_5 = item2_5;
    }

    public Integer getItem2_6() {
        return item2_6;
    }

    public void setItem2_6(Integer item2_6) {
        this.item2_6 = item2_6;
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
                ", quantity1=" + quantity1 +
                ", quantity2=" + quantity2 +
                ", item1_1=" + item1_1 +
                ", item1_2=" + item1_2 +
                ", item1_3=" + item1_3 +
                ", item1_4=" + item1_4 +
                ", item1_5=" + item1_5 +
                ", item1_6=" + item1_6 +
                ", item2_1=" + item2_1 +
                ", item2_2=" + item2_2 +
                ", item2_3=" + item2_3 +
                ", item2_4=" + item2_4 +
                ", item2_5=" + item2_5 +
                ", item2_6=" + item2_6 +
                '}';
    }
}
