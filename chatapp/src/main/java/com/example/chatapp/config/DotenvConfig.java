package com.example.chatapp.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test") // Don't load dotenv in test
public class DotenvConfig {

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL", "jdbc:postgresql://localhost:5432/Chat_java"));
        System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER", "postgres"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD", "I'mafkingb@dass"));
    }
}
