package com.example.restaurant.controllers;

import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.models.User;
import com.example.restaurant.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.restaurant.utils.MyTag.USER;

@RestController
@RequestMapping("/user")
@Tag(name = USER)
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Register account")
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody User user) {
        try {
            User savedUser = userService.register(user);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Registration successful",
                            savedUser));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(summary = "You need login with admin account to do this")
    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseObject> getAllUser() {
        Iterable<User> users = userService.getAllUsers();
        return ResponseEntity.ok(
                new ResponseObject(HttpStatus.OK.name(), "Get successfully", users));
    }
}
