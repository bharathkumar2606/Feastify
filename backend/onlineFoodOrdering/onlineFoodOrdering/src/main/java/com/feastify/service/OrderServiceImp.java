package com.feastify.service;

import com.feastify.Repository.AddressRepo;
import com.feastify.Repository.OrderItemRepo;
import com.feastify.Repository.OrderRepo;
import com.feastify.Repository.UserRepo;
import com.feastify.model.*;
import com.feastify.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress=order.getDeliverAddress();
        Address savedAddress=addressRepo.save(shippingAddress);

        if(!user.getAddress().contains(savedAddress)){
            user.getAddress().add(savedAddress);
            userRepo.save(user);
        }

        Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder=new Order();
        createdOrder.setCreatedAt(new Date());
        createdOrder.setCustomer(user);
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart=cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems=new ArrayList<>();

        for (CartItem cartItem:cart.getItem()){
            OrderItem orderItem=new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem=orderItemRepo.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice=cartService.calculateCartTotals(cart);


        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder=orderRepo.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
       Order order=findOrderById(orderId);
       if(orderStatus.equals("OUT_FOR_DELIVERY")
       || orderStatus.equals("DELIVERED")
       || orderStatus.equals("COMPLETED")
       || orderStatus.equals("PENDING")){
           order.setOrderStatus(orderStatus);
           return orderRepo.save(order);
       }
       throw new Exception("Please select a valid order status");
    }


    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order=findOrderById(orderId);
        orderRepo.deleteById(orderId);

    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepo.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus) throws Exception {
        List<Order> orders=orderRepo.findByRestaurantId(restaurantId);
        if (orderStatus!=null){
            orders=orders
                    .stream()
                    .filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder=orderRepo.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("order not found");
        }
        return optionalOrder.get();
    }
}