package com.feastify.service;

import com.feastify.dto.RestaurantDto;
import com.feastify.model.Restaurant;
import com.feastify.model.User;
import com.feastify.request.CreateRestaurantRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurantDetails(Long restaurantId,CreateRestaurantRequest updatedRestaurantDetails) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavourites(Long restaurantId,User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
