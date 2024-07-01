package com.feastify.controller;


import com.feastify.dto.RestaurantDto;
import com.feastify.model.Restaurant;
import com.feastify.model.User;
import com.feastify.request.CreateRestaurantRequest;
import com.feastify.service.RestaurantService;
import com.feastify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant=restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(
            @RequestHeader("Authorization") String jwt

    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant=restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity <Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @PutMapping("/{id}/add-favourites")
    public ResponseEntity <RestaurantDto> addTofavourites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        RestaurantDto restaurant=restaurantService.addToFavourites(id,user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }
}
