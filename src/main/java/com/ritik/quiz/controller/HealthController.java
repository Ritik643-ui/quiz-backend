package com.ritik.quiz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    /**
     * Provides a lightweight endpoint for verifying that the application is running.
     *
     * @return a simple health message
     */
    @GetMapping("/api/health")
    public String health(){
        return "OK!!!!";
    }
}
