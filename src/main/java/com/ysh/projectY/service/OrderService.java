package com.ysh.projectY.service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.ysh.projectY.dao.OrderDao;
import com.ysh.projectY.entity.Message;
import com.ysh.projectY.entity.Order;
import com.ysh.projectY.form.*;
import com.ysh.projectY.utils.MethodResponse;
import com.ysh.projectY.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class OrderService {

    MessageService messageService;
    final OrderDao orderDao;
    final PayPalHttpClient payPalHttpClient;

    public OrderService(MessageService messageService, OrderDao orderDao, PayPalHttpClient payPalHttpClient) {
        this.messageService = messageService;
        this.orderDao = orderDao;
        this.payPalHttpClient=payPalHttpClient;
    }

    public MethodResponse createOrder(CreateOrder createOrder) {
        Message message = new Message();
        final Timestamp utc = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("America/Vancouver")).getTime().getTime());
        message.setCreateDateTime(utc);
        String error = "";
        Order paypalOrder = new Order();
        paypalOrder.setClientOrderID(createOrder.getOrderId());
        paypalOrder.setName(createOrder.getName());
        paypalOrder.setTelePhone(createOrder.getTelephone());
        paypalOrder.setEmail(createOrder.getEmail());
        paypalOrder.setPostcode(createOrder.getPostcode());
        paypalOrder.setAddress(createOrder.getAddress());
        paypalOrder.setRemark(createOrder.getRemark());
        paypalOrder.setClientQuantity1(createOrder.getQuantity1());
        paypalOrder.setClientQuantity2(createOrder.getQuantity2());
        paypalOrder.setItem1_1(createOrder.getItem1_1());
        paypalOrder.setItem1_2(createOrder.getItem1_2());
        paypalOrder.setItem1_3(createOrder.getItem1_3());
        paypalOrder.setItem1_4(createOrder.getItem1_4());
        paypalOrder.setItem1_5(createOrder.getItem1_5());
        paypalOrder.setItem1_6(createOrder.getItem1_6());
        paypalOrder.setItem2_1(createOrder.getItem2_1());
        paypalOrder.setItem2_2(createOrder.getItem2_2());
        paypalOrder.setItem2_3(createOrder.getItem2_3());
        paypalOrder.setItem2_4(createOrder.getItem2_4());
        paypalOrder.setItem2_5(createOrder.getItem2_5());
        paypalOrder.setItem2_6(createOrder.getItem2_6());
        paypalOrder.setCreateDateTime(utc);
        OrdersGetRequest request = new OrdersGetRequest(createOrder.getOrderId());
        HttpResponse<com.paypal.orders.Order> response = null;
        try {

            try {
                response = payPalHttpClient.execute(request);
            } catch (IOException e) {
                error += "Network Error !";
                message.setSubject("Error Order");
                message.setMessage(error + e);
                messageService.save(message);
                e.printStackTrace();
                return MethodResponse.failure("projectY.OrderService.createOrder.failure.Exception", error + e);
            }
            if (response == null) {
                error += "Network response is NULL !";
                message.setSubject("Error Order");
                message.setMessage(error);
                messageService.save(message);
                return MethodResponse.failure("projectY.OrderService.createOrder.failure.Exception", error);
            }
            com.paypal.orders.Order order = response.result();
            if (order == null) {
                error += "Order information is NULL !";
                message.setSubject("Error Order");
                message.setMessage(error);
                messageService.save(message);
                return MethodResponse.failure("projectY.OrderService.createOrder.failure.Exception", error);
            }
            paypalOrder.setOrderID(StringUtils.isNull(order.id()));
            paypalOrder.setOrderType("Buy");
            paypalOrder.setIntent(StringUtils.isNull(order.checkoutPaymentIntent()));
            paypalOrder.setOrderStatus(StringUtils.isNull(order.status()));
            String ut = StringUtils.isNull(order.updateTime());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Timestamp updateDateTime = null;
            try {
                updateDateTime = new Timestamp(df.parse(ut).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            paypalOrder.setUpdateDateTime(StringUtils.isNull(updateDateTime));
            paypalOrder.setDescription("NULL");
            final List<PurchaseUnit> purchaseUnits = order.purchaseUnits();
            //为简化开发,只获取第一个商品的信息
            PurchaseUnit purchaseUnit = purchaseUnits.get(0);
            if (purchaseUnit == null) {
                error += "purchaseUnit Object is NULL !";
            }
            final Payee payee = purchaseUnit.payee();
            if (payee == null) {
                paypalOrder.setPayeeClientID(StringUtils.isNull(""));
                paypalOrder.setPayeeEmail(StringUtils.isNull(""));
                paypalOrder.setPayeeMerchantID(StringUtils.isNull(""));
            } else {
                paypalOrder.setPayeeClientID(StringUtils.isNull(payee.clientId()));
                paypalOrder.setPayeeEmail(StringUtils.isNull(payee.email()));
                paypalOrder.setPayeeMerchantID(StringUtils.isNull(payee.merchantId()));
            }
            final PayeeDisplayable payeeDisplayable = payee.payeeDisplayable();
            if (payeeDisplayable == null) {
                paypalOrder.setPayeeDisplayBrandName(StringUtils.isNull(""));
                paypalOrder.setPayeeDisplayBusinessPhone(StringUtils.isNull(""));
                paypalOrder.setPayeeDisplayEmail(StringUtils.isNull(""));
            } else {
                paypalOrder.setPayeeDisplayBrandName(StringUtils.isNull(payeeDisplayable.brandName()));
                final Phone phone = payeeDisplayable.businessPhone();
                String tmp = "" + phone.countryCallingCode() + " " + phone.nationalNumber() + " " + phone.extensionNumber();
                paypalOrder.setPayeeDisplayBusinessPhone(StringUtils.isNull(tmp));
                paypalOrder.setPayeeDisplayEmail(StringUtils.isNull(payeeDisplayable.email()));
            }
            final AmountWithBreakdown amountWithBreakdown = purchaseUnit.amountWithBreakdown();
            if (amountWithBreakdown == null) {
                paypalOrder.setAmountValue("");
                paypalOrder.setAmountCurrencyCode("");
            } else {
                paypalOrder.setAmountValue(StringUtils.isNull(amountWithBreakdown.value()));
                paypalOrder.setAmountCurrencyCode(StringUtils.isNull(amountWithBreakdown.currencyCode()));
            }
            //为简化开发,只计算合计的数量
            int quantity = 0;
            final List<Item> items = purchaseUnit.items();
            if (items != null) {
                for (final Item item : items) {
                    item.name();
                    quantity += Integer.valueOf(item.quantity());
                    final Money money = item.unitAmount();
                    money.value();
                    money.currencyCode();
                }
            } else {
                paypalOrder.setQuantity(quantity);
            }
            final Payer payer = order.payer();
            if (payer == null) {
                error += "payer is NULL !";
            } else {
                paypalOrder.setPayerID(StringUtils.isNull(payer.payerId()));
                final Name name = payer.name();
                if (name == null) {
                    paypalOrder.setPayerFullName(StringUtils.isNull(""));
                    paypalOrder.setPayerGivenName(StringUtils.isNull(""));
                    paypalOrder.setPayerSurname(StringUtils.isNull(""));
                } else {
                    paypalOrder.setPayerFullName(StringUtils.isNull(name.fullName()));
                    paypalOrder.setPayerGivenName(StringUtils.isNull(name.givenName()));
                    paypalOrder.setPayerSurname(StringUtils.isNull(name.surname()));
                }
                final PhoneWithType phoneWithType = payer.phoneWithType();
                if (phoneWithType == null) {
                    paypalOrder.setPayerPhoneType(StringUtils.isNull(""));
                    paypalOrder.setPayerPhoneNumber(StringUtils.isNull(""));
                } else {
                    paypalOrder.setPayerPhoneType(StringUtils.isNull(phoneWithType.phoneType()));
                    final Phone phone1 = phoneWithType.phoneNumber();
                    String tmp = "" + phone1.countryCallingCode() + " " + phone1.nationalNumber() + " " + phone1.extensionNumber();
                    paypalOrder.setPayerPhoneNumber(StringUtils.isNull(tmp));
                }
                paypalOrder.setPayerEmail(StringUtils.isNull(payer.email()));
                final AddressPortable addressPortable = payer.addressPortable();
                if (addressPortable == null) {
                    paypalOrder.setPayerCountryCode(StringUtils.isNull(""));
                    paypalOrder.setPayerPostalCode(StringUtils.isNull(""));
                    paypalOrder.setPayerAddressLine1(StringUtils.isNull(""));
                    paypalOrder.setPayerAddressLine2(StringUtils.isNull(""));
                    paypalOrder.setPayerAddressLine3(StringUtils.isNull(""));
                    paypalOrder.setPayerAdminArea1(StringUtils.isNull(""));
                    paypalOrder.setPayerAdminArea2(StringUtils.isNull(""));
                    paypalOrder.setPayerAdminArea3(StringUtils.isNull(""));
                    paypalOrder.setPayerAdminArea4(StringUtils.isNull(""));
                } else {
                    paypalOrder.setPayerCountryCode(StringUtils.isNull(addressPortable.countryCode()));
                    paypalOrder.setPayerPostalCode(StringUtils.isNull(addressPortable.postalCode()));
                    paypalOrder.setPayerAddressLine1(StringUtils.isNull(addressPortable.addressLine1()));
                    paypalOrder.setPayerAddressLine2(StringUtils.isNull(addressPortable.addressLine2()));
                    paypalOrder.setPayerAddressLine3(StringUtils.isNull(addressPortable.addressLine3()));
                    paypalOrder.setPayerAdminArea1(StringUtils.isNull(addressPortable.adminArea1()));
                    paypalOrder.setPayerAdminArea2(StringUtils.isNull(addressPortable.adminArea2()));
                    paypalOrder.setPayerAdminArea3(StringUtils.isNull(addressPortable.adminArea3()));
                    paypalOrder.setPayerAdminArea4(StringUtils.isNull(addressPortable.adminArea4()));
                }
            }
            orderDao.saveAndFlush(paypalOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.OrderService.createOrder.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.OrderService.createOrder.success");
    }

    public MethodResponse cancelOrder(CancelOrder cancelOrder) {
        Message message = new Message();
        message.setCreateDateTime(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime()));
        message.setSubject("Canceled Order !");
        message.setMessage("[" + cancelOrder.getOrderId() + "]: " + cancelOrder.getName() + ", " + cancelOrder.getTelePhone() + ", " + cancelOrder.getEmail() + ", " + cancelOrder.getPostcode() + ", " + cancelOrder.getAddress() + ", " + cancelOrder.getRemark() + ", " + cancelOrder.getQuantity1() + ", " + cancelOrder.getItem1_1() + ", " + cancelOrder.getItem1_2() + ", " + cancelOrder.getItem1_3() + ", " + cancelOrder.getItem1_4() + ", " + cancelOrder.getQuantity2() + ", " + cancelOrder.getItem2_1() + ", " + cancelOrder.getItem2_2() + ", " + cancelOrder.getItem2_3() + ", " + cancelOrder.getItem2_4());
        try {
            messageService.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.OrderService.cancelOrder.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.OrderService.cancelOrder.success");
    }

    public MethodResponse errorOrder(ErrorOrder errorOrder) {
        Message message = new Message();
        message.setCreateDateTime(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime()));
        message.setSubject("Wrong Order !");
        message.setMessage("[" + errorOrder.getError() + "]: " + errorOrder.getName() + ", " + errorOrder.getTelePhone() + ", " + errorOrder.getEmail() + ", " + errorOrder.getPostcode() + ", " + errorOrder.getAddress() + ", " + errorOrder.getRemark() + ", " + errorOrder.getQuantity1() + ", " + errorOrder.getItem1_1() + ", " + errorOrder.getItem1_2() + ", " + errorOrder.getItem1_3() + ", " + errorOrder.getItem1_4() + ", " + errorOrder.getQuantity2() + ", " + errorOrder.getItem2_1() + ", " + errorOrder.getItem2_2() + ", " + errorOrder.getItem2_3() + ", " + errorOrder.getItem2_4());
        messageService.save(message);
        try {
            messageService.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.OrderService.errorOrder.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.OrderService.errorOrder.success");
    }

    public Page<Order> getOrders(String name, String telePhone, String email, String orderId, Pageable pageable) {
        final Page<Order> page = orderDao.findAllByNameContainingAndTelePhoneContainingAndEmailContainingAndOrderIDContaining(name, telePhone, email, orderId, pageable);
        return page;
    }

    public Statistics getStatistics() {
        Statistics statistics = orderDao.getStatistics();
        System.out.println("statistics: " + statistics);
        return statistics;
    }

    @Transactional(rollbackFor = {Exception.class})
    public MethodResponse updateOrders(UpdateOrder updateOrder) {
        try {
            orderDao.updateOrders(updateOrder.getId(), updateOrder.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return MethodResponse.failure("projectY.OrderService.updateOrders.failure.Exception", e.toString());
        }
        return MethodResponse.success("projectY.OrderService.updateOrders.success");
    }
}
