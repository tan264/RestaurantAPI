package com.example.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseObject {
    private String status;

    private String message;

    private Object data;
}
