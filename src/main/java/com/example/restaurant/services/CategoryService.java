package com.example.restaurant.services;

import com.example.restaurant.models.Category;
import com.example.restaurant.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(int id, Category newCategory) {
        Category foundedCategory = categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Category not found for this id: " + id)
        );

        if(newCategory.getName() != null && !newCategory.getName().isBlank()) {
            foundedCategory.setName(newCategory.getName());
        }

        if(newCategory.getDescription() != null && !newCategory.getDescription().isBlank()) {
            foundedCategory.setDescription(newCategory.getDescription());
        }
        if(newCategory.getImageLink() != null && !newCategory.getImageLink().isBlank()) {
            foundedCategory.setImageLink(newCategory.getImageLink());
        }
        if(newCategory.getType() != foundedCategory.getType()) {
            foundedCategory.setType(newCategory.getType());
        }

        return categoryRepository.save(foundedCategory);
    }

    public Category deleteById(int id) {
        Category foundedCategory = categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Category with id = %d does not exist", id)));

        categoryRepository.deleteById(id);
        return foundedCategory;
    }
}
