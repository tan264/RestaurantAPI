package com.example.restaurant.controllers;

import com.example.restaurant.models.Category;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.CategoryService;
import com.example.restaurant.utils.MyTag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Operation(description = "This api will add a category into database", summary = "You need login with admin account to do this", tags = MyTag.CATEGORY)
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
}
