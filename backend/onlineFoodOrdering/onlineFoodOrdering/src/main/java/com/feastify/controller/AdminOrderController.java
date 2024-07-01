package com.feastify.controller;

import com.feastify.model.Order;
import com.feastify.model.User;
import com.feastify.request.OrderRequest;
import com.feastify.service.OrderService;
import com.feastify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_Status,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.getRestaurantOrders(id,order_Status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order orders=orderService.updateOrder(orderId,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
