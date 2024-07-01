package com.feastify.service;

import com.feastify.model.Food;
import com.feastify.model.FoodCategory;
import com.feastify.model.Restaurant;
import com.feastify.request.CreateFoodRequest;
import jdk.jfr.Category;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, FoodCategory category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFoods(Long restaurantId, // for filteration of food by user
                                         boolean isVeg,
                                         boolean isNonveg,
                                         String foodCategory,
                                         boolean isSeasonal);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailibilityStatus(Long foodId) throws Exception;
}
