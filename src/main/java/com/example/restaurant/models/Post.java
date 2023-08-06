package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ps_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "ps_title", length = 120, nullable = false)
    private String title;

    @Column(name = "ps_thumbnail_link", length = 120)
    private String thumbnailLink;

    @Column(name = "ps_created_date", columnDefinition = "DATETIME NOT NULL")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "ps_published")
    private byte published;

    @ManyToOne
    @JoinColumn(name = "cf_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "us_id")
    private User user;
}
