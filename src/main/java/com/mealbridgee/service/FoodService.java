package com.mealbridgee.service;

import com.mealbridgee.model.Category;
import com.mealbridgee.model.Food;
import com.mealbridgee.model.Restaurant;
import com.mealbridgee.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req , Category category, Restaurant restaurant);


    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegeterian,
                                        boolean isNonveg,
                                        boolean isSeasional,
                                        String foodCategory);


    public List<Food>searchFood(String keyword);

    public Food findFoodById(Long foodId)throws Exception;

    public Food updateAvailabilityStatus(Long foodId)throws Exception;


}
