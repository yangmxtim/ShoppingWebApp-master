package com.shoppingwebapp.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingwebapp.Model.Order_detail;

public interface OrderDetailService {
    Order_detail saveOrderDetail(Integer memberId) throws JsonProcessingException;

	void updatePaymentStatus(String ecpayOrderId, String paymentStatus);
}
