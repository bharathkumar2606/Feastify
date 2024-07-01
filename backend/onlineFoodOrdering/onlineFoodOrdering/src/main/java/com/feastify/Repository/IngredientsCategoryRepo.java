package com.feastify.Repository;

import com.feastify.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsCategoryRepo extends JpaRepository<IngredientCategory,Long> {

    List<IngredientCategory> findByRestaurantId(Long restaurantId);
}
