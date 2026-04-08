package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String sender;   // USER or BOT
    public String content;  // Message text or Image URL
    public String msgType;  // TEXT or IMAGE
    public LocalDateTime timestamp;
}