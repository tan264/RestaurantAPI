package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "food")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"name", "price", "categoryId", "userId"})
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fd_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private int id;

    @Column(name = "fd_name", length = 150, nullable = false)
    private String name;

    @Column(name = "fd_thumbnail", length = 120)
    @Schema(nullable = true)
    private String thumbnail;

    @Column(name = "fd_price", nullable = false)
    private int price;

    @Column(name = "fd_description", length = 65535)
    @Schema(nullable = true)
    private String description;

    @Column(name = "fd_youtube_link", length = 120)
    @Schema(nullable = true)
    private String youtubeLink;

    @Column(name = "cf_id", nullable = false)
    private int categoryId;

    @Column(name = "us_id", nullable = false)
    private int userId;

    @Schema(hidden = true)
    @ManyToOne
    @JoinColumn(name = "cf_id", insertable = false, updatable = false)
    private Category category;

    @Schema(hidden = true)
    @ManyToOne
    @JoinColumn(name = "us_id", insertable = false, updatable = false)
    private User user;

}
