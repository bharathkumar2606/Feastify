package com.feastify.request;

import com.feastify.model.IngredientsItem;
import com.feastify.model.FoodCategory;

import java.util.List;

public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private FoodCategory FoodCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public FoodCategory getFoodCategory() {
        return FoodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.FoodCategory = foodCategory;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isSeasonal() {
        return isSeasonal;
    }

    public void setSeasonal(boolean seasonal) {
        isSeasonal = seasonal;
    }

    public List<IngredientsItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsItem> ingredients) {
        this.ingredients = ingredients;
    }

    private List<String> images;
    private Long restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;

}
