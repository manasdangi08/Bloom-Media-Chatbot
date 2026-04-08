package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class BotService {

    public String getBotReply(String userMessage) {
        if (userMessage == null) return "Hello! I'm the Bloom Media Assistant. How can I help you today?";
String msg = userMessage.toLowerCase();


if (msg.contains("hello") || msg.contains("hi") || msg.contains("hey")) {
    return "Welcome to **Bloom Media**! We specialize in innovative digital solutions. How can I assist you with our services today?";
} 


else if (msg.contains("company") || msg.contains("bloom media") || msg.contains("who are you")) {
    return "Bloom Media is a dynamic agency dedicated to delivering high-quality Java-based backend solutions and digital media services.";
}


else if (msg.contains("project") || msg.contains("tech") || msg.contains("build")) {
    return "This chatbot is part of the **Bloom Media ecosystem**, developed using **Java, Spring Boot, and MySQL**. It demonstrates efficient string-matching algorithms and RESTful communication.";
}


else if (msg.contains("service") || msg.contains("help") || msg.contains("work")) {
    return "At Bloom Media, we provide expertise in **Backend Development (Java/Spring Boot)**, API integration, and scalable system architecture.";
}


else if (msg.contains("price") || msg.contains("cost") || msg.contains("hiring")) {
    return "For business inquiries or detailed pricing, please visit our <a href='https://bloommedia.com/contact' target='_blank' style='color: #2828a7; font-weight: bold;'>Contact Page</a>.";
}


else if (msg.contains("time")) {
    return "The current office time is " + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
}


else if (msg.contains("bye") || msg.contains("exit")) {
    return "Thank you for reaching out to Bloom Media. We look forward to working with you!";
}


else {
    return "I'm sorry, I didn't catch that. You can ask me about **Bloom Media's services**, our **tech stack**, or how to **contact us**.";
}
        }
    }
