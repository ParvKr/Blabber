package com.example.chatapp.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class SignalingController extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@SuppressWarnings("null") WebSocketSession session) {
        clients.put(session.getId(), session);
        System.out.println("New WebRTC connection: " + session.getId());
    }

    @Override
    protected void handleTextMessage(@SuppressWarnings("null") WebSocketSession session, @SuppressWarnings("null") TextMessage message) throws IOException {
        for (WebSocketSession client : clients.values()) {
            if (!client.getId().equals(session.getId())) {
                client.sendMessage(message); // Forward SDP/ICE messages
            }
        }
    }

    @Override
    public void afterConnectionClosed(@SuppressWarnings("null") WebSocketSession session, @SuppressWarnings("null") CloseStatus status) {
        clients.remove(session.getId());
        System.out.println("WebRTC connection closed: " + session.getId());
    }
}