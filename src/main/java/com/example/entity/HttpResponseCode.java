package com.example.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponseCode {

    public static ResponseEntity<String> createResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }

}
