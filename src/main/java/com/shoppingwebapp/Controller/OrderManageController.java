package com.shoppingwebapp.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Model.OrderInfo;
import com.shoppingwebapp.Service.OrderManageService;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

@RestController
public class OrderManageController {

	OrderManageService orderManageService;
	
	public OrderManageController(OrderManageService orderManageService) {
		this.orderManageService = orderManageService;
	}

	@GetMapping("/orderManage")
	public List<OrderInfo> findAllWithMember() {
		System.out.println("OrderManageController: findAllWithMember()");
		return orderManageService.findAllWithMember();
	}
	
}
