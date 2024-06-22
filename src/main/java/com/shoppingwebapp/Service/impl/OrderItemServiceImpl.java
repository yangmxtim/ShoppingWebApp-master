package com.shoppingwebapp.Service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingwebapp.DTO.OrderInfoDTO;
import com.shoppingwebapp.Dao.OrderDetailRepository;
import com.shoppingwebapp.Dao.OrderItemRepository;
import com.shoppingwebapp.Dao.ProductDetailRepository;
import com.shoppingwebapp.Dao.ProductRepository;
import com.shoppingwebapp.Model.Order_detail;
import com.shoppingwebapp.Model.Orderitem;
import com.shoppingwebapp.Model.Product_detail;
import com.shoppingwebapp.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<OrderInfoDTO> getOrderInfoById(Integer id) {
        return orderItemRepository.getOrderInfos(id);
    }

    @Override
    public List<Orderitem> finAllMembers() {
        return orderItemRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOrderItem(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        List<Orderitem> orderitemList = new ArrayList<>();
        //現在時間
        LocalDate currentDate = LocalDate.now();
        //票卷時間轉換
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        java.sql.Date sqlDate = null;
        for (JsonNode node : jsonNode) {
            JsonNode orderItemNode = node.elements().next();
            //商品編號
            Integer productDetailId = orderItemNode.get("skuId").asInt();
            //訂單編號（屬於哪張訂單
            Integer orderDetailId = orderItemNode.get("orderDetailId").asInt();
            //票卷日期
            String dateString = orderItemNode.get("selectedDate2").asText();
            java.util.Date utilDate =  sdf.parse(dateString);
            sqlDate = new java.sql.Date(utilDate.getTime());
            //使用狀態
            String status = "尚未使用";
            int count = orderItemNode.get("count").asInt();
            Order_detail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() -> new RuntimeException("OrderId not found"));
            Product_detail productDetail = productDetailRepository.findById(productDetailId).orElseThrow(() -> new RuntimeException("Product not found"));

            for (int i = 0; i < count; i++) {
                Orderitem orderitem = new Orderitem(status,sqlDate,orderDetail,productDetail,Date.valueOf(currentDate));
                orderitemList.add(orderitem);
            }
        }
        orderItemRepository.saveAll(orderitemList);
        System.out.println("order items saved" + orderitemList);
    }

}
