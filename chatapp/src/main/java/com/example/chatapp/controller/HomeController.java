package com.example.chatapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping(produces = "application/json")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the Chat App!");
        response.put("status", "running");
        return response;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Chat App is running!";
    }
}
