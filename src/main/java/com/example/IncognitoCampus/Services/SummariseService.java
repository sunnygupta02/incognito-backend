package com.example.IncognitoCampus.Services;

import com.example.IncognitoCampus.DTOS.SummariseRequest;
import com.example.IncognitoCampus.Models.ChatMessage;
import com.example.IncognitoCampus.Repos.ChatRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SummariseService {

    private final ChatRepo chatRepo;

    private final RestTemplate restTemplate;

    public String summarise(String roomId) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        Instant startOfDay = today.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endOfDay = today.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);

        List<ChatMessage> chatMessagess = chatRepo.findByRoomNameAndTimestampBetween(roomId, startOfDay, endOfDay);

        List<String> content = new ArrayList<>();

        for(ChatMessage chatMessage: chatMessagess){
            content.add(chatMessage.getContent());
        }

        System.out.println(content);
        SummariseRequest request = new SummariseRequest();
        request.setMessages(content);

// ✅ Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

// ✅ Make HTTP Entity
        HttpEntity<SummariseRequest> entity = new HttpEntity<>(request, headers);


        String summary = restTemplate.postForObject(
                "http://localhost:8081/api/summarise",
                entity,
                String.class
        );
        System.out.println(summary);
// Return or use summary as needed
        return summary;
        //return chatMessages;
    }
}
