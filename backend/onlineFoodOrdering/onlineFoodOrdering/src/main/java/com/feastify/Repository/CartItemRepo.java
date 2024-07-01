package com.feastify.Repository;

import com.feastify.model.Cart;
import com.feastify.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {

}
