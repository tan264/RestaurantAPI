package com.example.restaurant.controllers;

import com.example.restaurant.models.ChangeInfoUserForm;
import com.example.restaurant.models.ChangePasswordForm;
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

    @Operation(description = "Register account")
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
    @GetMapping("/get-all-user")
    public ResponseEntity<ResponseObject> getAllUser() {
        Iterable<User> users = userService.getAllUsers();
        return ResponseEntity.ok(
                new ResponseObject(HttpStatus.OK.name(), "Get successfully", users));
    }

    @Operation(summary = "You need login with admin account to do this")
    @PutMapping("/update-by-username")
    public ResponseEntity<ResponseObject> updateByUsername(@RequestParam String username,
            @RequestBody ChangeInfoUserForm form) {
        try {
            User updatedUser = userService.updateByUsername(username, form);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Update successfully",
                            updatedUser));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(summary = "You need login with admin account to do this")
    @PutMapping("/change-password")
    public ResponseEntity<ResponseObject> changePassword(@RequestBody ChangePasswordForm form) {
        try {
            User updatedUser = userService.changePassword(form.getUsername(), form.getOldPassword(),
                    form.getNewPassword());
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Change password successfully",
                            updatedUser));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(summary = "You need login with admin account to do this")
    @DeleteMapping("/delete-by-username")
    public ResponseEntity<ResponseObject> deleteByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Delete user successfully",
                            userService.deleteUserByUsername(username)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }
}
