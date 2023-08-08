package com.example.restaurant.controllers;

import com.example.restaurant.models.Image;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.restaurant.utils.MyTag.IMAGE;

@RestController
@RequestMapping("/image")
@Tag(name = IMAGE)
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/add")
    @Operation(summary = "Add Image")
    public ResponseEntity<ResponseObject> addImage(@RequestBody Image image) {
        try {
            Image addedImage = imageService.addImage(image);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Add book table successfully",
                            addedImage));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }
}
