package com.feastify.service;

import com.feastify.Repository.AddressRepo;
import com.feastify.Repository.RestaurantRepo;
import com.feastify.Repository.UserRepo;
import com.feastify.dto.RestaurantDto;
import com.feastify.model.Address;
import com.feastify.model.Restaurant;
import com.feastify.model.User;
import com.feastify.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService{
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        Address address=addressRepo.save(request.getAddress());

        Restaurant restaurant=new Restaurant();

        //restaurant.setId(request.getId());

        restaurant.setAddress(address);
        restaurant.setName(request.getName());
        restaurant.setContactInfo(request.getContactInfo());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurantDetails(Long restaurantId, CreateRestaurantRequest updatedRestaurantDetails) throws Exception {
        Restaurant restaurant=new Restaurant();
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurantDetails.getCuisineType());
        }
        if (restaurant.getDescription()!= null) {
            restaurant.setDescription(updatedRestaurantDetails.getDescription());
        }
        if (restaurant.getName()!=null){
            restaurant.setName(updatedRestaurantDetails.getName());
        }
        return restaurantRepo.save(restaurant);
    }


    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
            Restaurant restaurant=findRestaurantById(restaurantId);
            restaurantRepo.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt=restaurantRepo.findById(id);
        if(opt.isEmpty()){
            throw new Exception("restaurant not found with this id");
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant=restaurantRepo.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("restaurant not found with this owner id "+userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        RestaurantDto dto=new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setId(restaurantId);
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());

        boolean isFavourited=false;
        List<RestaurantDto> favourites=user.getFavourite();
        for (RestaurantDto fav:favourites){
            if(fav.getId().equals(restaurantId)){
                isFavourited=true;
                break;
            }
        }
        if(isFavourited){
            favourites.removeIf(fav->fav.getId().equals(restaurantId));
        }else favourites.add(dto); 
        userRepo.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepo.save(restaurant);
    }
}
