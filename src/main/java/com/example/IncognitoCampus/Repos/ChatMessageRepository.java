package com.example.IncognitoCampus.Repos;




import com.example.IncognitoCampus.Models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);

    List<ChatMessage> findByRoomNameOrderByTimestampAsc(String roomName);
}

