package com.shoppingwebapp.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingwebapp.Dao.OrderRepository;
import com.shoppingwebapp.Model.OrderInfo;
import com.shoppingwebapp.Service.OrderManageService;

@Service
public class OrderManageServiceImpl implements OrderManageService {
	
	private OrderRepository orderRepository;

	public OrderManageServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}


	@Override
	public List<OrderInfo> findBySearch(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInfo> findAllWithMember() {
		List<OrderInfo> orders = orderRepository.findAllWithMember();
		return orders;
	}

	@Override
	public OrderInfo save(OrderInfo bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderInfo update(OrderInfo bean) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public OrderInfo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<OrderInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
