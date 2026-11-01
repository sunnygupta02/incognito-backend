package com.example.IncognitoCampus.Controller;




import com.example.IncognitoCampus.Models.Room;
import com.example.IncognitoCampus.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Add a new room
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.ok(createdRoom);
    }

    // Get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get room by name
    @GetMapping("/name/{roomName}")
    public ResponseEntity<Room> getRoomByName(@PathVariable String roomName) {
        return roomService.getRoomByName(roomName)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomName));
    }

    // Optional: Get rooms by college
    @GetMapping("/college/{college}")
    public List<Room> getRoomsByCollege(@PathVariable String college) {
        return roomService.getRoomsByCollege(college);
    }


    @GetMapping("/search")
    public List<Room> searchRoomsByKeyword(@RequestParam String query) {
        return roomService.searchRoomsByNameKeyword(query);
    }
}

