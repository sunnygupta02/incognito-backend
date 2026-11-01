package com.example.IncognitoCampus.Services;

import com.example.IncognitoCampus.Models.ChatMessage;
import com.example.IncognitoCampus.Repos.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

//    public List<ChatMessage> getMessagesByRoomName(String roomId) {
//        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
//    }

    public List<ChatMessage> getMessagesByRoomName(String roomName) {
        return chatMessageRepository.findByRoomNameOrderByTimestampAsc(roomName);
    }

}
