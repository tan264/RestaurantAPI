package com.example.restaurant.controllers;

import com.example.restaurant.models.Food;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.restaurant.utils.MyTag.FOOD;

@RestController
@RequestMapping("/food")
@Tag(name = FOOD)
public class FoodController {

    @Autowired
    FoodService foodService;

    @Operation(description = "Add food")
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addFood(@RequestBody Food food) {
        try {
            Food addedFood = foodService.addFood(food);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Add food successfully",
                            addedFood));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(description = "Get food by id")
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getFoodById(@RequestParam int id) {
        Optional<Food> foundedFood = foodService.getFoodById(id);
        return foundedFood.map(food -> ResponseEntity.ok()
                .body(new ResponseObject(HttpStatus.OK.name(), "Get food successfully",
                        food))).orElseGet(() -> ResponseEntity.ok()
                .body(new ResponseObject(HttpStatus.OK.name(), "Not found food by id = " + id,
                        null)));
    }

    @Operation(description = "Delete food by id", summary = "You need login with admin account to do this")
    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ResponseObject> deleteFoodById(@RequestParam int id) {
        try {
            Food deletedFood = foodService.deleteFoodById(id);
            if (deletedFood != null) {
                return ResponseEntity.ok(
                        new ResponseObject(HttpStatus.OK.name(), "Delete food successfully",
                                deletedFood));
            } else {
                return ResponseEntity.ok(
                        new ResponseObject(HttpStatus.OK.name(), "Id " + id + " is not exists",
                                null));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(description = "Update food by id")
    @PutMapping("/update-by-id")
    public ResponseEntity<ResponseObject> updateFoodById(@RequestParam int id,
            @RequestBody Food food) {
        try {
            Food updatedFood = foodService.updateFood(id, food);
            return ResponseEntity.ok(
                    new ResponseObject(HttpStatus.OK.name(), "Update food successfully",
                            updatedFood));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(description = "Get all foods")
    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllFoods() {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Get all food successfully",
                            foodService.getAllFoods()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

}
