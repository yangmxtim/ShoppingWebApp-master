package com.shoppingwebapp.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingwebapp.Model.OrderInfo;
import com.shoppingwebapp.Model.Order_detail;
import com.shoppingwebapp.Service.OrderDetailService;
import com.shoppingwebapp.Service.OrderItemService;
import com.shoppingwebapp.Service.OrderService;

import jakarta.servlet.http.HttpServletResponse;

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
//        String orderId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
        
        // 將生成的訂單編號設置到OrderInfo對象中
        orderInfo.setOrderId(orderInfo.getEcpayOrderId());
        System.out.println(orderInfo);
        // 調用服務層處理訂單
        String aioCheckOutALLForm = orderService.ecpayCheckout(orderInfo);
        return aioCheckOutALLForm;
    }

    @CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
    @PostMapping("/generateOrderItems")
    public void saveOrderDetail(@RequestBody String jsonString) throws Exception {
        System.out.println(jsonString);
        orderItemService.saveOrderItem(jsonString);
        System.out.println("done");
    }

    @CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
    @PostMapping("generateOrder")
    public Map<String,Object> createOrderDetail(@RequestBody String jsonString) throws JsonProcessingException {
        System.out.println(jsonString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        Integer memberId = jsonNode.get("id").asInt();
        Order_detail orderDetail = orderDetailService.saveOrderDetail(memberId);

        Integer orderId = orderDetail.getOrder_id();
        String ecpayOrderId = orderDetail.getEcpayOrderId();
        Map<String,Object> response = new HashMap<>();
        response.put("orderId",orderId);
        response.put("ecpayOrderId",ecpayOrderId);

        return response;

    //        return orderDetailService.save(jsonString);
    }
    @PostMapping("/updateOrderStatus")
    public void getBack(@RequestParam String RtnCode, @RequestParam String MerchantTradeNo, HttpServletResponse response) throws IOException {
        System.out.println("Received payment callback for order: " + MerchantTradeNo);
        if ("1".equals(RtnCode)) {
            System.out.println("Payment successful for order: " + MerchantTradeNo + " OK");
            // 更新訂單狀態為成功
            orderDetailService.updatePaymentStatus(MerchantTradeNo, "已付款");

            // 跳轉至前端頁面
            response.sendRedirect("http://localhost:5173/qr?status=success&order=" + MerchantTradeNo);
        } else {
            System.out.println("Payment failed for order: " + MerchantTradeNo);
            // 更新訂單狀態為付款失敗
            orderDetailService.updatePaymentStatus(MerchantTradeNo, "付款失败");

            // 跳轉至前端頁面
            response.sendRedirect("http://localhost:5173/categoryMembers?status=failure&order=" + MerchantTradeNo);
        }
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

//@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端來源
//@PostMapping("generateOrder")
//public Integer createOrderDetail(@RequestBody String jsonString) throws JsonProcessingException {
//    System.out.println(jsonString);
//    ObjectMapper objectMapper = new ObjectMapper();
//    JsonNode jsonNode = objectMapper.readTree(jsonString);
//    Integer memberId = jsonNode.get("id").asInt();
//    orderDetailService.saveOrderDetail(memberId);
//    return orderDetailService.saveOrderDetail(memberId).getOrder_id();


