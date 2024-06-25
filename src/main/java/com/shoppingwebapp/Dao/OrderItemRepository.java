package com.shoppingwebapp.Dao;

import com.shoppingwebapp.DTO.OrderInfoDTO;

import com.shoppingwebapp.Model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
//oi.order_item_id ,m.username,od.contact_name ,od.payment_date ,od.payment_method , pd.price , p.name
public interface OrderItemRepository extends JpaRepository<Orderitem,Integer> {
    @Query( "SELECT new com.shoppingwebapp.DTO.OrderInfoDTO" +
            "(m.id, m.username,oi.date,od.payment_method, od.total_amount, oi.order_item_id, pd.price, pd.product_detail_id, pd.name, p.name,pd.img,oi.status, oi.ticket_date)" +
            " FROM Member m " +
            " JOIN  m.order_detail od " +
            " JOIN  od.orderitem oi " +
            " JOIN  oi.productdetail pd " +
            " JOIN  pd.product p " +
            "WHERE m.id = :memberId and od.payment_status = '已付款'and oi.status = '尚未使用'  " +
            "AND oi.ticket_date > :ticketDate " +

            "ORDER BY oi.order_item_id  ")
    List<OrderInfoDTO> getOrderInfos(@Param("memberId") int id, @Param("ticketDate") Date ticketDate);

    @Modifying
    @Transactional
    @Query("UPDATE Orderitem oi SET oi.status = '已使用' WHERE oi.order_item_id = :id")
    int updateContentById(@Param("id") Integer id);

    @Query("select oi from Orderitem oi where oi.order_item_id = :id")
    Orderitem findOrderItemByOrderItemId(@Param("id") Integer orderItemId);
}