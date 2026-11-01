package com.example.IncognitoCampus.DTOS;


import lombok.Data;

@Data
public class LoginResponse {

    private String id;
    private String email;
    private String displayName;
    private String college;
    private String branch;
}
