package com.feastify.service;

import com.feastify.Repository.IngredientsCategoryRepo;
import com.feastify.Repository.IngredientsItemRepo;
import com.feastify.model.IngredientCategory;
import com.feastify.model.IngredientsItem;
import com.feastify.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class IngredientServiceImp implements IngredientsService{

    @Autowired
    private IngredientsItemRepo ingredientsItemRepo;

    @Autowired
    private IngredientsCategoryRepo ingredientsCategoryRepo;


    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientcategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category=new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        return ingredientsCategoryRepo.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt=ingredientsCategoryRepo.findById(id);

        if (opt.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id); // it throws exception if restaurant is not found by id
        return ingredientsCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory=findIngredientCategoryById(categoryId);

        IngredientsItem item=new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(ingredientCategory);

        IngredientsItem ingredientsItem=ingredientsItemRepo.save(item);
        ingredientCategory.getIngredients().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientsItemRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem=ingredientsItemRepo.findById(id);
        if (optionalIngredientsItem.isEmpty()){
            throw new Exception("ingredient not found");
        }
        IngredientsItem ingredientsItem=optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepo.save(ingredientsItem);
    }
}
