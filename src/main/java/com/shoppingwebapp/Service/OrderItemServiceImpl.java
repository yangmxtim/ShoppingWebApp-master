package com.shoppingwebapp.Service;

import com.shoppingwebapp.DTO.OrderInfoDTO;
import com.shoppingwebapp.Dao.OrderItemRepository;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Model.Orderitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository orderItemRepository;
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

}
