package com.mealbridgee.controller;

import com.mealbridgee.model.Food;
import com.mealbridgee.model.Restaurant;
import com.mealbridgee.model.User;
import com.mealbridgee.request.CreateFoodRequest;
import com.mealbridgee.response.MessageResponse;
import com.mealbridgee.service.FoodService;
import com.mealbridgee.service.RestaurantService;
import com.mealbridgee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food>createFood(@RequestBody CreateFoodRequest req,
                                          @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());
        Food food=foodService.createFood(req,req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>deleteFood(@PathVariable Long id,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("food deleted succefully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Food>updateFoodAvaibilityStatus(@PathVariable Long id,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

       Food food= foodService.updateAvailabilityStatus(id);


        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
