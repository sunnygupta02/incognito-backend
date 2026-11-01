package com.example.IncognitoCampus.Services;




import com.example.IncognitoCampus.Models.Room;
import com.example.IncognitoCampus.Repos.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create a room (with unique room name)
    public Room createRoom(Room room) {
        if (roomRepository.findByNameIgnoreCase(room.getName()).isPresent()) {
            throw new IllegalArgumentException("Room name already exists: " + room.getName());
        }

        return roomRepository.save(room);
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get room by name
    public Optional<Room> getRoomByName(String roomName) {
        return roomRepository.findByNameIgnoreCase(roomName);
    }

    // Get rooms by college
    public List<Room> getRoomsByCollege(String college) {
        return roomRepository.findByCollegeIgnoreCase(college);
    }

    public List<Room> searchRoomsByNameKeyword(String keyword) {
        return roomRepository.findByNameContainingIgnoreCase(keyword);
    }
}
