package com.feastify.controller;

import com.feastify.model.Food;
import com.feastify.model.Restaurant;
import com.feastify.model.User;
import com.feastify.request.CreateFoodRequest;
import com.feastify.response.MessageResponse;
import com.feastify.service.FoodService;
import com.feastify.service.RestaurantService;
import com.feastify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false) String foodCategory,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.getRestaurantFoods(restaurantId,vegetarian,nonVeg,foodCategory,seasonal);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}
