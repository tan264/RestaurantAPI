package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"title", "categoryId", "userId"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ps_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private int id;

    @Column(name = "ps_title", length = 120, nullable = false)
    private String title;

    @Column(name = "ps_thumbnail_link", length = 120)
    @Schema(nullable = true)
    private String thumbnailLink;

    @Column(name = "ps_created_date", columnDefinition = "DATETIME NOT NULL")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "ps_published")
    @Schema(description = "0: not published - 1: published", type = "number")
    private byte isPublished = 0;

    @Column(name = "cf_id", nullable = false)
    private int categoryId;

    @Column(name = "us_id", nullable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "cf_id", insertable = false, updatable = false)
    @Schema(hidden = true)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "us_id", insertable = false, updatable = false)
    @Schema(hidden = true)
    private User user;
}
