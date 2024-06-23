package com.shoppingwebapp.Service;

import com.shoppingwebapp.Model.OrderInfo;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.stereotype.Service;
/*
                綠界API
*/
@Service
public class OrderService {

    public String ecpayCheckout(OrderInfo orderInfo) {
        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(orderInfo.getOrderId());
        obj.setMerchantTradeDate("2017/01/01 08:05:23"); // 這裡應該使用實際的交易時間
        obj.setTotalAmount(orderInfo.getTotalAmount());
        obj.setTradeDesc(orderInfo.getTradeDesc());
        obj.setItemName(orderInfo.getItemName());
        obj.setReturnURL("http://211.23.128.214:5000"); // 回傳的URL
        obj.setClientBackURL("http://localhost:5173/categoryMembers");
        obj.setOrderResultURL("http://localhost:8080/updateOrderStatus");
        obj.setNeedExtraPaidInfo("N");
        String form = all.aioCheckOut(obj, null);

        return form;
    }
}
