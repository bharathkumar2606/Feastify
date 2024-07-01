package com.feastify.service;

import com.feastify.Repository.CartItemRepo;
import com.feastify.Repository.CartRepo;
import com.feastify.Repository.FoodRepo;
import com.feastify.model.Cart;
import com.feastify.model.CartItem;
import com.feastify.model.Food;
import com.feastify.model.User;
import com.feastify.request.AddCartItemRequest;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Food food=foodService.findFoodById(request.getFoodId());

        Cart cart=cartRepo.findByCustomerId(user.getId());

        for (CartItem cartItem:cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity=cartItem.getQuantity()+request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);

            }
        }
        CartItem newCartItem=new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity()*food.getPrice());

        CartItem savedCartItem=cartItemRepo.save(newCartItem);

        cart.getItem().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional=cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem item=cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepo.save(item);

    }

    @Override
    public Cart removeCartItem(Long cartItemId, String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Cart cart=cartRepo.findByCustomerId(user.getId());
        Optional<CartItem> cartItemOptional=cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem cartItem=cartItemOptional.get();
        cart.getItem().remove(cartItem);
        return cartRepo.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total=0L;
        for (CartItem cartItem:cart.getItem()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> optionalCart=cartRepo.findById(id);
        if (optionalCart.isEmpty()){
            throw new Exception("Cart not Found "+id);
        }

        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

        //User user=userService.findUserByJwtToken(jwt);
        Cart cart= cartRepo.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        //User user=userService.findUserByJwtToken(jwt);
        Cart cart=findCartByUserId(userId);
        cart.getItem().clear();
        return cartRepo.save(cart);
    }
}
