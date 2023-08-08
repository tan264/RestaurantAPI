package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(requiredProperties = {"name"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cf_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private int id;

    @Column(name = "cf_name", nullable = false, length = 120)
    private String name;

    @Column(name = "cf_image_link", length = 120)
    @Schema(nullable = true)
    private String imageLink;

    @Column(name = "cf_description", length = 65535)
    @Schema(nullable = true)
    private String description;

    @Column(name = "cf_type")
    @Schema(type = "number")
    private byte type;
}
