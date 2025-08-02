package com.mealbridgee.service;

import com.mealbridgee.model.Category;
import com.mealbridgee.model.Food;
import com.mealbridgee.model.Restaurant;
import com.mealbridgee.repository.FoodRepository;
import com.mealbridgee.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodServiceImp implements FoodService{


    @Autowired
    private FoodRepository foodRepository;




    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food=new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarian());


        Food savedFood= foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegeterian,
                                        boolean isNonveg,
                                        boolean isSeasional,
                                        String foodCategory) {

        List<Food> foods=foodRepository.findByRestaurantId(restaurantId);


        if(isVegeterian){
            foods=filterByVegetarian(foods,isVegeterian );
        }
        if(isNonveg)
        {
            foods=filterByNonveg(foods,isNonveg);
        }
        if(isSeasional){
            foods=filterBySeasional(foods,isSeasional);
        }

        if(foodCategory!=null&&!foodCategory.equals("")){
            foods=filterBycategory(foods,foodCategory);
        }


        return foods;
    }

    private List<Food> filterBycategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasional(List<Food> foods, boolean isSeasional) {

        return foods.stream().filter(food -> food.isSeasonal()==isSeasional).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {

        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegeterian) {

        return foods.stream().filter(food -> food.isVegetarian()==isVegeterian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food>optionalFood=foodRepository.findById(foodId);
        if(optionalFood.isEmpty())
        {
            throw new Exception("Food not exists");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {

        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);


    }
}
