package com.example.restaurant.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"foodId", "userId", "numberPeople"})
public class BookTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bt_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private int id;

    @Column(name = "fd_id", nullable = false)
    private int foodId;

    @Column(name = "us_id", nullable = false)
    private int userId;

    @Column(name = "bt_datetime", columnDefinition = "DATETIME NOT NULL")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "auto generated")
    private LocalDateTime dateTime = LocalDateTime.now();

    @Column(name = "bt_number_people", nullable = false)
    private int numberPeople;

    @Column(name = "bt_note", length = 65535)
    @Schema(nullable = true)
    private String note;

    @ManyToOne
    @JoinColumn(name = "fd_id", insertable = false, updatable = false)
    @Schema(hidden = true)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "us_id", insertable = false, updatable = false)
    @Schema(hidden = true)
    private User user;
}
