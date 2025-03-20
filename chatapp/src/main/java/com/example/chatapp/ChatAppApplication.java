package com.example.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ChatAppApplication {

    private static final Logger logger = LoggerFactory.getLogger(ChatAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChatAppApplication.class, args);
        logger.info("🚀 Chat App has started successfully!");
    }
}
