package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"foodId"})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "im_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "im_link")
    @Schema(nullable = true)
    private String link;

    @Column(name = "fd_id", nullable = false)
    private int foodId;

    @ManyToOne
    @JoinColumn(name = "fd_id", insertable = false, updatable = false)
    @Schema(hidden = true)
    private Food food;
}
