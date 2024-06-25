package com.shoppingwebapp.Controller;

import com.shoppingwebapp.DTO.OrderInfoDTO;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Model.Orderitem;
import com.shoppingwebapp.Service.OrderItemService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = {"Origin", "Content-Type", "Accept"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;


    @GetMapping("/orderlists/{id}")
    public List<OrderInfoDTO> findMemberWithOrdersAndProducts(@PathVariable int id) {
        return orderItemService.getOrderInfoById(id);
    }
    @GetMapping("/orderlists")
    public List<Orderitem> finaAll() {
        return orderItemService.finAllMembers();
    }

    //test qr code
    @GetMapping("/qr/{order_item_id}")
    public String updateNoteContent(@PathVariable Integer order_item_id, HttpServletResponse response) throws IOException {
        int updatedRows = orderItemService.updateContentById(order_item_id);
        if (updatedRows > 0) {
            return "ok";
        } else {
            return "no";
        }
    }
}
