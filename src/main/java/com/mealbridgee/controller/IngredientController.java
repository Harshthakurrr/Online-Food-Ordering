package com.mealbridgee.controller;

import com.mealbridgee.model.IngredientCategory;
import com.mealbridgee.model.IngredientsItem;
import com.mealbridgee.request.IngredientCategoryRequest;
import com.mealbridgee.request.IngredientRequest;
import com.mealbridgee.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    private ResponseEntity<IngredientCategory>createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {

        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(),req.getRestaurantId());

        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @PostMapping()
    private ResponseEntity<IngredientsItem>createIngredientItem(
            @RequestBody IngredientRequest req
            ) throws Exception {

        IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId());


        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @PutMapping("/{id}/stock")
    private ResponseEntity<IngredientsItem>updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {

        IngredientsItem item = ingredientsService.updateStock(id);


        return new ResponseEntity<>(item, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}")
    private ResponseEntity <List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {

        List<IngredientsItem> items = ingredientsService.findRestaurantIngredients(id);


        return new ResponseEntity<>(items, HttpStatus.OK);

    }


    @GetMapping("/restaurant/{id}/category")
    private ResponseEntity <List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {

        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(items, HttpStatus.OK);

    }


}
