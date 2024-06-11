package com.shoppingwebapp.Service;

import com.shoppingwebapp.DTO.OrderInfoDTO;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Model.Orderitem;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderItemService {
    List<OrderInfoDTO> getOrderInfoById(Integer id);

    List<Orderitem> finAllMembers();
}
