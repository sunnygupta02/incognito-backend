package com.example.IncognitoCampus.Models;

// model/User.java
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();

    private String email;
    private String displayName;
    private String college;
    private String password;
    private String branch;
    private Instant createdAt = Instant.now();

    List<Room> userRooms=new ArrayList<>();
}

