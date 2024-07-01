package com.feastify.service;

import com.feastify.model.Order;
import com.feastify.model.User;
import com.feastify.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrders(Long userId) throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;


}
