package com.feastify.Repository;

import com.feastify.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsItemRepo extends JpaRepository<IngredientsItem,Long> {

List<IngredientsItem> findByRestaurantId(Long restaurantId);

}
