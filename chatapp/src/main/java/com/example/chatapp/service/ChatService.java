package com.example.chatapp.service;

import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    // Save message to database
    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    // Retrieve chat history from database
    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAll();
    }
}
