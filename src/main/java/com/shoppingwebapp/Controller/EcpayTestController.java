package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Model.OrderInfo;
import com.shoppingwebapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EcpayTestController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
    @PostMapping("/ecpayCheckout")
    public String ecpayCheckout(@RequestBody OrderInfo orderInfo) {
        // 使用UUID來生成唯一訂單編號
        String orderId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
        
        // 將生成的訂單編號設置到OrderInfo對象中
        orderInfo.setOrderId(orderId);
        
        // 調用服務層處理訂單
        String aioCheckOutALLForm = orderService.ecpayCheckout(orderInfo);
        return aioCheckOutALLForm;
    }
}

