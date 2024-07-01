package com.feastify.Repository;

import com.feastify.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
    public Cart findByCustomerId(Long userId);
}
