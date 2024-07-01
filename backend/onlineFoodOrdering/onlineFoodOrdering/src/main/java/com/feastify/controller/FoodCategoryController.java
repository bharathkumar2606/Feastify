package com.feastify.controller;

import com.feastify.model.FoodCategory;
import com.feastify.model.User;
import com.feastify.service.FoodCategoryService;
import com.feastify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<FoodCategory> createCategory(@RequestBody FoodCategory foodCategory,
                                                       @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        FoodCategory createFoodCategory =foodCategoryService.createCategory(foodCategory.getName(),
                foodCategory.getId());

        return new ResponseEntity<>(createFoodCategory, HttpStatus.CREATED);


    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<FoodCategory>> getRestaurantCategory(
                                                       @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user=userService.findUserByJwtToken(jwt);

       List<FoodCategory> createFoodCategory =foodCategoryService.findCategoryByRestaurantId(user.getId());

        return new ResponseEntity<>(createFoodCategory, HttpStatus.CREATED);


    }
}
