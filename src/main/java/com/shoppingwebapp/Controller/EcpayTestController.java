package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcpayTestController {

        @Autowired
        OrderService orderService;

        @PostMapping("/ecpayCheckout")
        public String ecpayCheckout() {
            String aioCheckOutALLForm = orderService.ecpayCheckout();

            return aioCheckOutALLForm;
        }


}
