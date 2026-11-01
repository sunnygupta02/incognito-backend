package com.example.IncognitoCampus.DTOS;

import lombok.Data;

import java.util.List;

@Data
public class SummariseRequest {
    private List<String> messages;
}

