package com.example.chatapp.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class SignalingController extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> clients = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SignalingController.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        clients.put(session.getId(), session);
        logger.info("New WebRTC connection: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        for (WebSocketSession client : clients.values()) {
            if (!client.getId().equals(session.getId())) {
                try {
                    client.sendMessage(message);
                } catch (IOException e) {
                    logger.error("Error sending WebSocket message: {}", e.getMessage());
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        try {
            clients.remove(session.getId());
            logger.info("WebRTC connection closed: {}", session.getId());
        } finally {
            try {
                session.close();
            } catch (IOException e) {
                logger.error("Error closing WebSocket session: {}", e.getMessage());
            }
        }
    }
}
