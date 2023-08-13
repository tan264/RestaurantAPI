package com.example.restaurant.controllers;

import com.example.restaurant.models.Category;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.restaurant.utils.MyTag.CATEGORY;

@RestController
@RequestMapping("/category")
@Tag(name = CATEGORY)
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Operation(description = "This api will add a category into database", summary = "You need login with admin account to do this")
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Add category successfully",
                            savedCategory));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null
                    ));
        }
    }

    @Operation(description = "Get all category")
    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllCategory() {
        try {
            Iterable<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(),
                            "Get all categories successfully",
                            categories));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null
                    ));
        }
    }

    @Operation(summary = "You need login with admin account to do this", description = "Edit category")
    @PutMapping("/update-by-id")
    public ResponseEntity<ResponseObject> updateCategoryById(@RequestParam int id,
            @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(id, category);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(),
                            "Update category successfully",
                            updatedCategory));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null
                    ));
        }
    }

    @Operation(summary = "You need login with admin account to do this")
    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ResponseObject> deleteById(@RequestParam int id) {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Delete category successfully",
                            categoryService.deleteById(id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }
}
