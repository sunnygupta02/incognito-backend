package com.example.IncognitoCampus.Repos;

import com.example.IncognitoCampus.Models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface ChatRepo extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomNameAndTimestampBetween(String roomId, Instant start, Instant end);

}
