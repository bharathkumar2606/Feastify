package com.feastify.controller;

import com.feastify.model.Cart;
import com.feastify.model.CartItem;
import com.feastify.model.User;
import com.feastify.request.AddCartItemRequest;
import com.feastify.request.UpdateCartItemRequest;
import com.feastify.service.CartService;
import com.feastify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-api")
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private UserService userService;

    @PutMapping("/add-to-cart")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddCartItemRequest request,
            @RequestHeader("Authorization") String jwt
            )throws Exception{
        CartItem cartItem=cartService.addItemToCart(request,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest request,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        CartItem cartItem=cartService.updateCartItemQuantity(request.getCartItemId(),request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(
            @PathVariable Long cartItemid,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        Cart cart=cartService.removeCartItem(cartItemid,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(

            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(

            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }



}
