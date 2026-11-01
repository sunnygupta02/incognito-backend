package com.example.IncognitoCampus.Controller;

import com.example.IncognitoCampus.Services.SummariseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summarise")
@AllArgsConstructor
public class SummariseController {

    private final SummariseService summariseService;

    @GetMapping
    public ResponseEntity<String> summarise(@RequestParam String roomId){
        System.out.println(roomId);
        return new ResponseEntity<>(summariseService.summarise(roomId), HttpStatus.OK);
    }
}
