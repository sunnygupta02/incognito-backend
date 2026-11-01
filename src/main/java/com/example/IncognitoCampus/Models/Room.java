package com.example.IncognitoCampus.Models;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
        private String name;       // e.g., IIT Bombay - CSE
        private String college;
        private String branch;
        private String Description;
}

