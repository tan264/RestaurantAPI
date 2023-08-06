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

    public Food updateFood(int id, Food savedFood) {
        Food food = foodRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Food not found for this id: " + id));

        food.setCategoryId(savedFood.getCategoryId());
        if (savedFood.getName() != null && !savedFood.getName().isBlank()) {
            food.setName(savedFood.getName());
        }
        food.setPrice(savedFood.getPrice());
        if (savedFood.getDescription() != null && !savedFood.getDescription().isBlank()) {
            food.setDescription(savedFood.getDescription());
        }
        if (savedFood.getThumbnail() != null && !savedFood.getThumbnail().isBlank()) {
            food.setThumbnail(savedFood.getThumbnail());
        }
        if (savedFood.getYoutubeLink() != null && !savedFood.getYoutubeLink().isBlank()) {
            food.setYoutubeLink(savedFood.getYoutubeLink());
        }
        food.setUserId(savedFood.getUserId());
        return foodRepository.save(food);
    }
}
