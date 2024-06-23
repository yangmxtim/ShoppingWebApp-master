package com.shoppingwebapp.Service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Dao.OrderDetailRepository;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Model.Order_detail;
import com.shoppingwebapp.Service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, MemberRepository memberRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Order_detail saveOrderDetail(Integer memberId) throws JsonProcessingException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

        // Generate ecpayOrderId
        String orderId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

        Order_detail orderDetail = new Order_detail();
        orderDetail.setOrder_date(Date.valueOf(LocalDate.now()));
        orderDetail.setMember(member);
        orderDetail.setPayment_method("信用卡");
        orderDetail.setPayment_status("未付款");
        orderDetail.setEcpayOrderId(orderId);

        return orderDetailRepository.save(orderDetail);
    }

    @Override
    @Transactional
    public void updatePaymentStatus(String ecpayOrderId, String paymentStatus) {
        Order_detail orderDetail = orderDetailRepository.findByEcpayOrderId(ecpayOrderId);
        if (orderDetail != null) {
            orderDetail.setPayment_status(paymentStatus);
            orderDetailRepository.save(orderDetail);
        } else {
            throw new RuntimeException("Order not found for ecpayOrderId: " + ecpayOrderId);
        }
    }
}
