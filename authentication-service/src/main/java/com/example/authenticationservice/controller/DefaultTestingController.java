package com.example.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DefaultTestingController {
    // Define a GET endpoint that returns a response
    @GetMapping
    public ResponseEntity<?> defaultResponse() {
        // Create a ResponseEntity with a message and an HTTP status code  with 220 OK
        return new ResponseEntity<>("It is Working....", HttpStatus.OK);
    }

}
