package com.shoppingwebapp.Service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Dao.OrderDetailRepository;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Model.Order_detail;
import com.shoppingwebapp.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
    @Autowired
    private MemberRepository memberRepository;
    @Override
    @Transactional
    public Order_detail saveOrderDetail(Integer memberId) throws JsonProcessingException {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("Member Not Found"));
        LocalDate currentDate = LocalDate.now();
        //產生綠界編號
        String orderId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

        Order_detail orderDetail = new Order_detail(Date.valueOf(currentDate),member,"信用卡","未付款",orderId);
        return orderDetailRepository.save(orderDetail);
    }
}
