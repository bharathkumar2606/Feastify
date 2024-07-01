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

@RestController
@RequestMapping("admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/add-food")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(request.getRestaurantId());
        Food food=foodService.createFood(request,request.getFoodCategory(),restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long foodId,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        MessageResponse response=new MessageResponse();
        response.setMessage("Food Deleted successfully");
        foodService.deleteFood(foodId);
        return new ResponseEntity<>(response,HttpStatus.OK);


    }
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailibilityStatus(@PathVariable Long foodId,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);


        Food food=foodService.updateAvailibilityStatus(foodId);

        return new ResponseEntity<>(food,HttpStatus.CREATED);


    }
}
