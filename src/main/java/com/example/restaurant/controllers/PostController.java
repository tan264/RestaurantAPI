package com.example.restaurant.controllers;

import com.example.restaurant.models.Post;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.restaurant.utils.MyTag.POSTS;

@RestController
@RequestMapping("post")
@Tag(name = POSTS)
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/add")
    @Operation(description = "Add post")
    public ResponseEntity<ResponseObject> addPost(@RequestBody Post post) {
        try {
            Post addedPost = postService.addPost(post);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Add post successfully",
                            addedPost));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @GetMapping("/get-all")
    @Operation(description = "Get all posts")
    public ResponseEntity<ResponseObject> getAllPosts() {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Add post successfully",
                            postService.getAllPosts()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(summary = "You need login with admin account to do this")
    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ResponseObject> deleteById(@RequestParam int id) {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Delete post successfully",
                            postService.deleteById(id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

}
