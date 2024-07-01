package com.feastify.service;

import com.feastify.model.Food;
import com.feastify.model.FoodCategory;

import java.util.List;

public interface FoodCategoryService {

    public FoodCategory createCategory(String name,Long userId) throws Exception;

    public List<FoodCategory> findCategoryByRestaurantId(Long id) throws Exception;

    public FoodCategory findFoodCategoryById(Long id) throws Exception;
}
