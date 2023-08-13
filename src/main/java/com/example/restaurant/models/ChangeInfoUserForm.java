package com.example.restaurant.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeInfoUserForm {

    @Column(name = "us_full_name", length = 45)
    @Schema(nullable = true)
    private String fullName;

    @Column(name = "us_address", length = 120)
    @Schema(nullable = true)
    private String address;

    @Column(name = "us_mobile", nullable = false, length = 10)
    @Schema(nullable = true)
    private String mobile;

    @Column(name = "us_email", length = 45)
    @Schema(nullable = true)
    private String email;
}
