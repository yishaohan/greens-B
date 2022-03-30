package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Order;
import com.ysh.projectY.form.*;
import com.ysh.projectY.service.OrderService;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/public/orders")
    public HttpEntity<?> createOrder(@Valid @RequestBody CreateOrder createOrder) {
        final MethodResponse methodResponse = orderService.createOrder(createOrder);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @PostMapping("/public/cancelOrder")
    public HttpEntity<?> cancelOrder(@Valid @RequestBody CancelOrder cancelOrder) {
        final MethodResponse methodResponse = orderService.cancelOrder(cancelOrder);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @PostMapping("/public/errorOrder")
    public HttpEntity<?> errorOrder(@Valid @RequestBody ErrorOrder errorOrder) {
        final MethodResponse methodResponse = orderService.errorOrder(errorOrder);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }

    @GetMapping("/user/orders")
    public HttpEntity<?> getOrders(@RequestParam(name = "name", defaultValue = "") String name,
                                   @RequestParam(name = "telePhone", defaultValue = "") String telePhone,
                                   @RequestParam(name = "email", defaultValue = "") String email,
//                                   @RequestParam(name = "status", defaultValue = "") String status,
                                   @RequestParam(name = "orderId", defaultValue = "") String orderId,
                                   @RequestParam(name = "current", defaultValue = "1") int current,
                                   @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        if (current <= 0) {
            current = 1;
        }
        if (pageSize <= 0) {
            pageSize = 1;
        }
        if (pageSize > 1000) {
            pageSize = 1000;
        }
        Pageable pageable = PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        final Page<Order> users = orderService.getOrders(name, telePhone, email, orderId, pageable);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.UserController.getUsers.success", users, users.toString()), HttpStatus.OK);
    }

    @GetMapping("/user/statistics")
    public HttpEntity<?> getStatistics() {
        Statistics statistics = orderService.getStatistics();
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.UserController.getUsers" +
                ".success", statistics), HttpStatus.OK);
    }

    @PutMapping("/user/orders")
    public HttpEntity<?> updateOrders(@Valid @RequestBody UpdateOrder updateOrder) {
        final MethodResponse methodResponse = orderService.updateOrders(updateOrder);
        if (!methodResponse.isSuccess()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.CREATED.value(), methodResponse.getI18nMessageKey(), methodResponse.getData(), methodResponse.getDetail()), HttpStatus.CREATED);
    }
}
