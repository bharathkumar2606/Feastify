package com.feastify.service;

import com.feastify.Repository.FoodRepo;
import com.feastify.model.Food;
import com.feastify.model.FoodCategory;
import com.feastify.model.Restaurant;
import com.feastify.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepo repo;
    @Override
    public Food createFood(CreateFoodRequest req, FoodCategory category, Restaurant restaurant) {
        Food food=new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setIngredients(req.getIngredients());
        food.setPrice(req.getPrice());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());

        Food savedFood=repo.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setRestaurant(null);
        repo.save(food);

    }


    @Override
    public List<Food> getRestaurantFoods(Long restaurantId,
                                         boolean isVeg,
                                         boolean isNonveg,
                                         String foodCategory,
                                         boolean isSeasonal) {
        List<Food> foods=repo.findByRestaurantId(restaurantId);
        if (isVeg) foods = filterByVegetarian(foods, isVeg);
        if (isNonveg) foods = filterByNonVegetarian(foods, isNonveg);
        if (isSeasonal) foods = filterBySeasonal(foods, isSeasonal);
        if (foodCategory != null && !foodCategory.isEmpty()) foods = filterByCategory(foods, foodCategory);
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods,String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null) {
                food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;


        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food->food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food->food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVeg) {
        return foods.stream().filter(food->food.isVegetarian()==isVeg).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood=repo.findById(foodId);
        if(optionalFood.isEmpty()) throw new Exception("food not found...");
        return optionalFood.get();
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return repo.save(food);

    }
}
