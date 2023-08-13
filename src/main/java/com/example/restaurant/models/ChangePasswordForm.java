package com.example.restaurant.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(requiredProperties = {"username", "oldPassword", "newPassword"})
public class ChangePasswordForm {

    private String username;

    private String oldPassword;

    private String newPassword;
}
