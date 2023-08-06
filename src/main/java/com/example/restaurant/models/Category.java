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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cf_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "cf_name", nullable = false, length = 120)
    private String name;

    @Column(name = "cf_image_link", length = 120)
    private String imageLink;

    @Column(name = "cf_description", length = 65535)
    private String description;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "cf_type")
    private byte type;
}
