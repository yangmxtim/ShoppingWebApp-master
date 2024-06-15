package com.shoppingwebapp.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingwebapp.Model.OrderInfo;
import com.shoppingwebapp.Service.OrderDetailService;
import com.shoppingwebapp.Service.OrderItemService;
import com.shoppingwebapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
public class EcpayTestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderItemService orderItemService;

    @CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout(@RequestBody OrderInfo orderInfo) {
        // 使用UUID來生成唯一訂單編號
        String orderId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
        
        // 將生成的訂單編號設置到OrderInfo對象中
        orderInfo.setOrderId(orderId);
        System.out.println(orderInfo);
        // 調用服務層處理訂單
        String aioCheckOutALLForm = orderService.ecpayCheckout(orderInfo);
        return aioCheckOutALLForm;
    }

    @CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
    @PostMapping("/generateOrderItems")
    public void saveOrderDetail(@RequestBody String jsonString) throws IOException {
        System.out.println(jsonString);
        orderItemService.saveOrderItem(jsonString);
        System.out.println("done");
    }

    @CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
    @PostMapping("generateOrder")
    public Integer createOrderDetail(@RequestBody String jsonString) throws JsonProcessingException {
        System.out.println(jsonString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        Integer memberId = jsonNode.get("id").asInt();
        return orderDetailService.saveOrderDetail(memberId).getOrder_id();

    //        return orderDetailService.save(jsonString);
    }

}

