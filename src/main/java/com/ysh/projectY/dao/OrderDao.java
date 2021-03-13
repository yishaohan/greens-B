package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor {

    Page<Order> findAllByNameContainingAndTelePhoneContainingAndEmailContainingAndOrderIDContaining(String name, String telePhone, String email, String orderId, Pageable pageable);

    @Modifying
    @Query(value = "update order_info set status=:status where id=:id", nativeQuery = true)
    void updateOrders(int id, boolean status);
}
