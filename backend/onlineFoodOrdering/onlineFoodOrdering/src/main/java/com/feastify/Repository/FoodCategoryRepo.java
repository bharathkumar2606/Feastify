package com.feastify.Repository;

import com.feastify.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodCategoryRepo extends JpaRepository<FoodCategory,Long> {


    public List<FoodCategory> findCategoryByRestaurantId(Long id);
}
