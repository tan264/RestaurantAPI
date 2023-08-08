package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data()
@AllArgsConstructor
@NoArgsConstructor
@Schema(requiredProperties = {"username", "password", "mobile"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private int id;

    @Column(name = "us_username", nullable = false, length = 45, unique = true)
    @Schema()
    private String username;

    @Column(name = "us_password", nullable = false, length = 120)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "us_full_name", length = 45)
    @Schema(nullable = true)
    private String fullName;

    @Column(name = "us_address", length = 120)
    @Schema(nullable = true)
    private String address;

    @Column(name = "us_mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "us_status")
    @Schema(description = "0: inactive - 1: active", type = "number", defaultValue = "1")
    private byte status = 1;

    @Column(name = "us_created_date", columnDefinition = "DATETIME NOT NULL")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "us_update_password")
    @Schema(type = "number", description = "0: not updated - 1: updated")
    private byte isUpdatedPassword = 0;

    @Column(name = "us_group", nullable = false)
    @Schema(description = "0: user - 1: admin", type = "number")
    private byte group = 0;

    @Column(name = "us_email", length = 45)
    @Schema(nullable = true)
    private String email;

    @JsonIgnore
    public String getRole() {
        if (group == 1) {
            return "ADMIN";
        }
        return "USER";
    }
}
