package com.devsec.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller with security best practices
 */
@RestController
public class Controller {
    
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @GetMapping("/")
public String home() {
    return "Welcome to the DevSecOps Dashboard! You are successfully authenticated.";
}

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        logger.info("Health check endpoint called");
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "DevSecOps App");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/data")
    public ResponseEntity<Map<String, String>> processData(@RequestBody Map<String, String> request) {
        logger.info("Processing data request");
        
        // Input validation
        if (request == null || request.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Data processed successfully");
        response.put("timestamp", System.currentTimeMillis() + "");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/version")
    public ResponseEntity<Map<String, String>> version() {
        Map<String, String> response = new HashMap<>();
        response.put("version", "1.0.0");
        response.put("environment", System.getProperty("app.environment", "production"));
        return ResponseEntity.ok(response);
    }
}
