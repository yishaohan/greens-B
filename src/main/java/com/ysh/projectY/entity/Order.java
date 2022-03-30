package com.ysh.projectY.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "order_info")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", length = 32, nullable = false)
    private int id;

    @Column(name = "`client_order_id`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '订单ID'")
    private String clientOrderID;

    @Column(name = "`name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '用户名称'")
    private String name;

    @Column(name = "`tele_phone`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '电话'")
    private String telePhone;

    @Column(name = "`email`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮件'")
    private String email;

    @Column(name = "`postcode`", columnDefinition = "varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮编'")
    private String postcode;

    @Column(name = "`address`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '地址'")
    private String address;

    @Column(name = "`remark`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '备注'")
    private String remark;

    @Column(name = "`client_quantity1`", columnDefinition = "int NOT NULL comment '数量1'")
    private int clientQuantity1;

    @Column(name = "`client_quantity2`", columnDefinition = "int NOT NULL comment '数量2'")
    private int clientQuantity2;

    @ColumnDefault("0")
    @Column(name = "`item1_1`", columnDefinition = "int NOT NULL comment '商品1_1'")
    private int item1_1;

    @ColumnDefault("0")
    @Column(name = "`item1_2`", columnDefinition = "int NOT NULL comment '商品1_2'")
    private int item1_2;

    @ColumnDefault("0")
    @Column(name = "`item1_3`", columnDefinition = "int NOT NULL comment '商品1_3'")
    private int item1_3;

    @ColumnDefault("0")
    @Column(name = "`item1_4`", columnDefinition = "int NOT NULL comment '商品1_4'")
    private int item1_4;

    @ColumnDefault("0")
    @Column(name = "`item1_5`", columnDefinition = "int NOT NULL comment '商品1_5'")
    private int item1_5;

    @ColumnDefault("0")
    @Column(name = "`item1_6`", columnDefinition = "int NOT NULL comment '商品1_6'")
    private int item1_6;

    @ColumnDefault("0")
    @Column(name = "`item2_1`", columnDefinition = "int NOT NULL comment '商品2_1'")
    private int item2_1;

    @ColumnDefault("0")
    @Column(name = "`item2_2`", columnDefinition = "int NOT NULL comment '商品2_2'")
    private int item2_2;

    @ColumnDefault("0")
    @Column(name = "`item2_3`", columnDefinition = "int NOT NULL comment '商品2_3'")
    private int item2_3;

    @ColumnDefault("0")
    @Column(name = "`item2_4`", columnDefinition = "int NOT NULL comment '商品2_4'")
    private int item2_4;

    @ColumnDefault("0")
    @Column(name = "`item2_5`", columnDefinition = "int NOT NULL comment '商品2_5'")
    private int item2_5;

    @ColumnDefault("0")
    @Column(name = "`item2_6`", columnDefinition = "int NOT NULL comment '商品2_6'")
    private int item2_6;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Vancouver")
    @Column(name = "`create_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP comment '订单创建时间'")
    private Timestamp createDateTime;

    @ColumnDefault("0")
    @Column(name = "`status`", columnDefinition = "tinyint(1) NOT NULL comment '是否发货'")
    private boolean status;

    // Paypal订单信息
    @Column(name = "`order_id`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '订单ID'")
    private String orderID;

    @Column(name = "`order_type`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '订单类型'")
    private String orderType;

    @Column(name = "`intent`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '订单意图'")
    private String intent;
    /**
     * CREATED. The order was created with the specified context.
     * // SAVED. The order was saved and persisted. The order status continues to be in progress until a capture is made with final_capture = true for all purchase units within the order.
     * // APPROVED. The customer approved the payment through the PayPal wallet or another form of guest or unbranded payment. For example, a card, bank account, or so on.
     * // VOIDED. All purchase units in the order are voided.
     * // COMPLETED. The payment was authorized or the authorized payment was captured for the order.
     * // PAYER_ACTION_REQUIRED. The order requires an action from the payer (e.g. 3DS authentication). Redirect the payer to the "rel":"payer-action" HATEOAS link returned as part of the response prior to authorizing or capturing the order.
     * // Read only.
     */
    @Column(name = "`order_status`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '订单状态'")
    private String orderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "`update_date_time`", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP comment '订单更新时间'")
    private Timestamp updateDateTime;

    @Column(name = "`quantity`", columnDefinition = "int comment '订单数量'")
    private int quantity;

    @Column(name = "`amount_value`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '订单金额'")
    private String amountValue;

    @Column(name = "`amount_currency_code`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '货币代码'")
    private String amountCurrencyCode;

    @Column(name = "`description`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '描述'")
    private String description;

    @Column(name = "`payer_id`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '付款人ID'")
    private String payerID;

    @Column(name = "`payer_full_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人姓名'")
    private String payerFullName;

    @Column(name = "`payer_given_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人姓名'")
    private String payerGivenName;

    @Column(name = "`payer_surname`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人姓名'")
    private String payerSurname;

    @Column(name = "`payer_phone_type`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人电话类型'")
    private String payerPhoneType;

    @Column(name = "`payer_phone_number`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人电话号码'")
    private String payerPhoneNumber;

    @Column(name = "`payer_email`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人电子邮件'")
    private String payerEmail;

    @Column(name = "`payer_country_code`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人国家代码'")
    private String payerCountryCode;

    @Column(name = "`payer_postal_code`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人邮政编码'")
    private String payerPostalCode;

    @Column(name = "`payer_admin_area1`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人区域'")
    private String payerAdminArea1;

    @Column(name = "`payer_admin_area2`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人区域'")
    private String payerAdminArea2;

    @Column(name = "`payer_admin_area3`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人区域'")
    private String payerAdminArea3;

    @Column(name = "`payer_admin_area4`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人区域'")
    private String payerAdminArea4;

    @Column(name = "`payer_address_line1`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人地址'")
    private String payerAddressLine1;

    @Column(name = "`payer_address_line2`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人地址'")
    private String payerAddressLine2;

    @Column(name = "`payer_address_line3`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '付款人地址'")
    private String payerAddressLine3;

    @Column(name = "`payee_client_id`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '收款人ID'")
    private String payeeClientID;

    @Column(name = "`payee_email`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '收款人电子邮件'")
    private String payeeEmail;

    @Column(name = "`payee_merchant_id`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '收款人商家ID'")
    private String payeeMerchantID;

    @Column(name = "`payee_display_brand_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '收款人显示的品牌名称'")
    private String payeeDisplayBrandName;

    @Column(name = "`payee_display_business_phone`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '收款人显示的电话号码'")
    private String payeeDisplayBusinessPhone;

    @Column(name = "`payee_display_email`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci comment '收款人显示的电子邮件'")
    private String payeeDisplayEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientOrderID() {
        return clientOrderID;
    }

    public void setClientOrderID(String clientOrderID) {
        this.clientOrderID = clientOrderID;
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

    public int getClientQuantity1() {
        return clientQuantity1;
    }

    public void setClientQuantity1(int clientQuantity) {
        this.clientQuantity1 = clientQuantity;
    }

    public int getClientQuantity2() {
        return clientQuantity2;
    }

    public void setClientQuantity2(int clientQuantity2) {
        this.clientQuantity2 = clientQuantity2;
    }

    public int getItem1_1() {
        return item1_1;
    }

    public void setItem1_1(int item1_1) {
        this.item1_1 = item1_1;
    }

    public int getItem1_2() {
        return item1_2;
    }

    public void setItem1_2(int item1_2) {
        this.item1_2 = item1_2;
    }

    public int getItem1_3() {
        return item1_3;
    }

    public void setItem1_3(int item1_3) {
        this.item1_3 = item1_3;
    }

    public int getItem1_4() {
        return item1_4;
    }

    public void setItem1_4(int item1_4) {
        this.item1_4 = item1_4;
    }

    public int getItem1_5() {
        return item1_5;
    }

    public void setItem1_5(int item1_5) {
        this.item1_5 = item1_5;
    }

    public int getItem1_6() {
        return item1_6;
    }

    public void setItem1_6(int item1_6) {
        this.item1_6 = item1_6;
    }

    public int getItem2_1() {
        return item2_1;
    }

    public void setItem2_1(int item2_1) {
        this.item2_1 = item2_1;
    }

    public int getItem2_2() {
        return item2_2;
    }

    public void setItem2_2(int item2_2) {
        this.item2_2 = item2_2;
    }

    public int getItem2_3() {
        return item2_3;
    }

    public void setItem2_3(int item2_3) {
        this.item2_3 = item2_3;
    }

    public int getItem2_4() {
        return item2_4;
    }

    public void setItem2_4(int item2_4) {
        this.item2_4 = item2_4;
    }

    public int getItem2_5() {
        return item2_5;
    }

    public void setItem2_5(int item2_5) {
        this.item2_5 = item2_5;
    }

    public int getItem2_6() {
        return item2_6;
    }

    public void setItem2_6(int item2_6) {
        this.item2_6 = item2_6;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = amountValue;
    }

    public String getAmountCurrencyCode() {
        return amountCurrencyCode;
    }

    public void setAmountCurrencyCode(String amountCurrencyCode) {
        this.amountCurrencyCode = amountCurrencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getPayerFullName() {
        return payerFullName;
    }

    public void setPayerFullName(String payerFullName) {
        this.payerFullName = payerFullName;
    }

    public String getPayerGivenName() {
        return payerGivenName;
    }

    public void setPayerGivenName(String payerGivenName) {
        this.payerGivenName = payerGivenName;
    }

    public String getPayerSurname() {
        return payerSurname;
    }

    public void setPayerSurname(String payerSurname) {
        this.payerSurname = payerSurname;
    }

    public String getPayerPhoneType() {
        return payerPhoneType;
    }

    public void setPayerPhoneType(String payerPhoneType) {
        this.payerPhoneType = payerPhoneType;
    }

    public String getPayerPhoneNumber() {
        return payerPhoneNumber;
    }

    public void setPayerPhoneNumber(String payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerCountryCode() {
        return payerCountryCode;
    }

    public void setPayerCountryCode(String payerCountryCode) {
        this.payerCountryCode = payerCountryCode;
    }

    public String getPayerPostalCode() {
        return payerPostalCode;
    }

    public void setPayerPostalCode(String payerPostalCode) {
        this.payerPostalCode = payerPostalCode;
    }

    public String getPayerAdminArea1() {
        return payerAdminArea1;
    }

    public void setPayerAdminArea1(String payerAdminArea1) {
        this.payerAdminArea1 = payerAdminArea1;
    }

    public String getPayerAdminArea2() {
        return payerAdminArea2;
    }

    public void setPayerAdminArea2(String payerAdminArea2) {
        this.payerAdminArea2 = payerAdminArea2;
    }

    public String getPayerAdminArea3() {
        return payerAdminArea3;
    }

    public void setPayerAdminArea3(String payerAdminArea3) {
        this.payerAdminArea3 = payerAdminArea3;
    }

    public String getPayerAdminArea4() {
        return payerAdminArea4;
    }

    public void setPayerAdminArea4(String payerAdminArea4) {
        this.payerAdminArea4 = payerAdminArea4;
    }

    public String getPayerAddressLine1() {
        return payerAddressLine1;
    }

    public void setPayerAddressLine1(String payerAddressLine1) {
        this.payerAddressLine1 = payerAddressLine1;
    }

    public String getPayerAddressLine2() {
        return payerAddressLine2;
    }

    public void setPayerAddressLine2(String payerAddressLine2) {
        this.payerAddressLine2 = payerAddressLine2;
    }

    public String getPayerAddressLine3() {
        return payerAddressLine3;
    }

    public void setPayerAddressLine3(String payerAddressLine3) {
        this.payerAddressLine3 = payerAddressLine3;
    }

    public String getPayeeClientID() {
        return payeeClientID;
    }

    public void setPayeeClientID(String payeeClientID) {
        this.payeeClientID = payeeClientID;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public String getPayeeMerchantID() {
        return payeeMerchantID;
    }

    public void setPayeeMerchantID(String payeeMerchantID) {
        this.payeeMerchantID = payeeMerchantID;
    }

    public String getPayeeDisplayBrandName() {
        return payeeDisplayBrandName;
    }

    public void setPayeeDisplayBrandName(String payeeDisplayBrandName) {
        this.payeeDisplayBrandName = payeeDisplayBrandName;
    }

    public String getPayeeDisplayBusinessPhone() {
        return payeeDisplayBusinessPhone;
    }

    public void setPayeeDisplayBusinessPhone(String payeeDisplayBusinessPhone) {
        this.payeeDisplayBusinessPhone = payeeDisplayBusinessPhone;
    }

    public String getPayeeDisplayEmail() {
        return payeeDisplayEmail;
    }

    public void setPayeeDisplayEmail(String payeeDisplayEmail) {
        this.payeeDisplayEmail = payeeDisplayEmail;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientOrderID='" + clientOrderID + '\'' +
                ", name='" + name + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", email='" + email + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", clientQuantity1=" + clientQuantity1 +
                ", clientQuantity2=" + clientQuantity2 +
                ", item1_1=" + item1_1 +
                ", item1_2=" + item1_2 +
                ", item1_3=" + item1_3 +
                ", item1_4=" + item1_4 +
                ", item2_1=" + item2_1 +
                ", item2_2=" + item2_2 +
                ", item2_3=" + item2_3 +
                ", item2_4=" + item2_4 +
                ", createDateTime=" + createDateTime +
                ", status=" + status +
                ", orderID='" + orderID + '\'' +
                ", orderType='" + orderType + '\'' +
                ", intent='" + intent + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", updateDateTime=" + updateDateTime +
                ", quantity=" + quantity +
                ", amountValue='" + amountValue + '\'' +
                ", amountCurrencyCode='" + amountCurrencyCode + '\'' +
                ", description='" + description + '\'' +
                ", payerID='" + payerID + '\'' +
                ", payerFullName='" + payerFullName + '\'' +
                ", payerGivenName='" + payerGivenName + '\'' +
                ", payerSurname='" + payerSurname + '\'' +
                ", payerPhoneType='" + payerPhoneType + '\'' +
                ", payerPhoneNumber='" + payerPhoneNumber + '\'' +
                ", payerEmail='" + payerEmail + '\'' +
                ", payerCountryCode='" + payerCountryCode + '\'' +
                ", payerPostalCode='" + payerPostalCode + '\'' +
                ", payerAdminArea1='" + payerAdminArea1 + '\'' +
                ", payerAdminArea2='" + payerAdminArea2 + '\'' +
                ", payerAdminArea3='" + payerAdminArea3 + '\'' +
                ", payerAdminArea4='" + payerAdminArea4 + '\'' +
                ", payerAddressLine1='" + payerAddressLine1 + '\'' +
                ", payerAddressLine2='" + payerAddressLine2 + '\'' +
                ", payerAddressLine3='" + payerAddressLine3 + '\'' +
                ", payeeClientID='" + payeeClientID + '\'' +
                ", payeeEmail='" + payeeEmail + '\'' +
                ", payeeMerchantID='" + payeeMerchantID + '\'' +
                ", payeeDisplayBrandName='" + payeeDisplayBrandName + '\'' +
                ", payeeDisplayBusinessPhone='" + payeeDisplayBusinessPhone + '\'' +
                ", payeeDisplayEmail='" + payeeDisplayEmail + '\'' +
                '}';
    }
}
