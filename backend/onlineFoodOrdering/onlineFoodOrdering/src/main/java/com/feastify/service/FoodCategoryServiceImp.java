package com.feastify.service;

import com.feastify.Repository.CartRepo;
import com.feastify.Repository.FoodCategoryRepo;
import com.feastify.model.FoodCategory;
import com.feastify.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryServiceImp implements FoodCategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodCategoryRepo foodCategoryRepo;

    @Override
    public FoodCategory createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(userId);
        FoodCategory foodCategory=new FoodCategory();
        foodCategory.setName(name);
        foodCategory.setRestaurant(restaurant);
        return foodCategoryRepo.save(foodCategory);
    }

    @Override
    public List<FoodCategory> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(id);

        return foodCategoryRepo.findCategoryByRestaurantId(restaurant.getId());
    }

    @Override
    public FoodCategory findFoodCategoryById(Long id) throws Exception {
        Optional<FoodCategory> optionalFoodCategory=foodCategoryRepo.findById(id);
        if(optionalFoodCategory.isEmpty()){
            throw new Exception("category not found");
        }
        return optionalFoodCategory.get();
    }
}
