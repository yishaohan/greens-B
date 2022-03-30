package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Order;
import com.ysh.projectY.form.Statistics;
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

    @Query(value = "select sum(client_quantity1)           as quantity1,\n" +
            "       sum(client_quantity2)           as quantity2,\n" +
            "       sum(item1_1 * client_quantity1) as item1_1,\n" +
            "       sum(item1_2 * client_quantity1) as item1_2,\n" +
            "       sum(item1_3 * client_quantity1) as item1_3,\n" +
            "       sum(item1_4 * client_quantity1) as item1_4,\n" +
            "       sum(item1_5 * client_quantity1) as item1_5,\n" +
            "       sum(item1_6 * client_quantity1) as item1_6,\n" +
            "       sum(item2_1 * client_quantity2) as item2_1,\n" +
            "       sum(item2_2 * client_quantity2) as item2_2,\n" +
            "       sum(item2_3 * client_quantity2) as item2_3,\n" +
            "       sum(item2_4 * client_quantity2) as item2_4,\n" +
            "       sum(item2_5 * client_quantity2) as item2_5,\n" +
            "       sum(item2_6 * client_quantity2) as item2_6\n" +
            "from order_info", nativeQuery = true)
    Statistics getStatistics();
}
