package com.example.IncognitoCampus.Repos;

// repository/RoomRepository.java

import com.example.IncognitoCampus.Models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {

    Optional<Room> findByName(String name);
    List<Room> findByCollege(String college);

    Optional<Room> findById(String roomId);

    Optional<Room> findByNameIgnoreCase(String name);

    List<Room> findByCollegeIgnoreCase(String college);

    //List<Room> findByNameRegexIgnoreCase(String regex);

    List<Room> findByNameContainingIgnoreCase(String keyword);


}

