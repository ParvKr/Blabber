package com.example.chatapp.repository;

import com.example.chatapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySender(String sender);

    @Query("SELECT c FROM ChatMessage c ORDER BY c.id DESC LIMIT ?1")
    List<ChatMessage> findLatestMessages(int limit);
}