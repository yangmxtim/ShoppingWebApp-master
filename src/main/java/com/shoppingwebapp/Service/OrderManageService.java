package com.shoppingwebapp.Service;

import java.util.List;

import com.shoppingwebapp.Model.OrderInfo;

public interface OrderManageService {

	OrderInfo findById(int id);
	
	List<OrderInfo> findBySearch(String searchText);

	List<OrderInfo> findAll();

	OrderInfo save(OrderInfo bean);
	
	OrderInfo update(OrderInfo bean);

	List<OrderInfo> findAllWithMember(); 

}
