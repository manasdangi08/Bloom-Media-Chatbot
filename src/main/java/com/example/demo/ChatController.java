package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Allows Frontend to access Backend
public class ChatController {

    @Autowired
    private ChatRepository repo;

    @Autowired
    private BotService botService;

    // Defines folder path: ProjectFolder/uploads
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    // 1. Get All Messages (Load History)
    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return repo.findAll();
    }

    // 2. Send Text Message
    @PostMapping("/send")
    public ChatMessage sendMessage(@RequestBody ChatMessage msg) {
        msg.timestamp = LocalDateTime.now();
        if (msg.sender == null) msg.sender = "USER";
        
        // Save User Message
        ChatMessage savedMsg = repo.save(msg);

        // Logic: If it's a TEXT message, ask BotService for a reply
        if ("TEXT".equalsIgnoreCase(msg.msgType)) {
            String reply = botService.getBotReply(msg.content);

            ChatMessage botMsg = new ChatMessage();
            botMsg.sender = "BOT";
            botMsg.content = reply;
            botMsg.msgType = "TEXT";
            botMsg.timestamp = LocalDateTime.now().plusSeconds(1); // Slight delay for realism
            repo.save(botMsg);
        }

        return savedMsg;
    }

    // 3. Upload File (Image or Audio)
    @PostMapping("/upload")
    public ChatMessage uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // Create folder if it doesn't exist
        File directory = new File(UPLOAD_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate unique name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
        
        // Save file to disk
        Files.write(filePath, file.getBytes());

        // Create Database Entry
        ChatMessage msg = new ChatMessage();
        msg.sender = "USER";
        msg.content = "/uploads/" + fileName; // URL path for frontend
        msg.timestamp = LocalDateTime.now();

        // --- DETECT FILE TYPE ---
        String name = fileName.toLowerCase();
        if (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif")) {
            msg.msgType = "IMAGE";
        } 
        else if (name.endsWith(".mp3") || name.endsWith(".wav") || name.endsWith(".webm") || name.endsWith(".ogg")) {
            msg.msgType = "AUDIO";
        } 
        else {
            msg.msgType = "FILE"; // PDF, Doc, etc.
        }

        ChatMessage savedMsg = repo.save(msg);

        // Bot Acknowledges Receipt
        ChatMessage botMsg = new ChatMessage();
        botMsg.sender = "BOT";
        if (msg.msgType.equals("AUDIO")) {
            botMsg.content = "Received your voice message.";
        } else {
            botMsg.content = "File received: " + file.getOriginalFilename();
        }
        botMsg.msgType = "TEXT";
        botMsg.timestamp = LocalDateTime.now().plusSeconds(1);
        repo.save(botMsg);

        return savedMsg;
    }

    // 4. Delete Message
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        // Check if exists to avoid errors
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}