package com.example.IncognitoCampus.DTOS;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String college;
    private String branch;
}
