package com.feastify.controller;

import com.feastify.model.Restaurant;
import com.feastify.model.User;
import com.feastify.request.CreateRestaurantRequest;
import com.feastify.response.MessageResponse;
import com.feastify.service.RestaurantService;
import com.feastify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwt
            )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.createRestaurant(request,user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.updateRestaurantDetails(id,request);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);


        restaurantService.deleteRestaurant(id);

        MessageResponse response=new MessageResponse();
        response.setMessage("restaurant deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt

    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

}
