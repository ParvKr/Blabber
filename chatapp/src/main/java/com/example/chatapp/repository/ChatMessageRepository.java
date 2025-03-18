package com.example.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chatapp.model.ChatMessage;

@Repository 
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
