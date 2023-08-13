package com.example.restaurant.services;

import com.example.restaurant.models.Food;
import com.example.restaurant.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    public Optional<Food> getFoodById(int id) {
        return foodRepository.findById(id);
    }

    public Food deleteFoodById(int id) {
        Optional<Food> foundedFood = foodRepository.findById(id);
        if (foundedFood.isPresent()) {
            foodRepository.deleteById(id);
            return foundedFood.get();
        } else {
            return null;
        }
    }

    public Food updateFood(int id, Food newFood) {
        Food food = foodRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Food not found for this id: " + id));

        food.setCategoryId(newFood.getCategoryId());
        if (newFood.getName() != null && !newFood.getName().isBlank()) {
            food.setName(newFood.getName());
        }
        food.setPrice(newFood.getPrice());
        if (newFood.getDescription() != null && !newFood.getDescription().isBlank()) {
            food.setDescription(newFood.getDescription());
        }
        if (newFood.getThumbnail() != null && !newFood.getThumbnail().isBlank()) {
            food.setThumbnail(newFood.getThumbnail());
        }
        if (newFood.getYoutubeLink() != null && !newFood.getYoutubeLink().isBlank()) {
            food.setYoutubeLink(newFood.getYoutubeLink());
        }
        food.setUserId(newFood.getUserId());
        return foodRepository.save(food);
    }

    public Iterable<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
