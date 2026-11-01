package com.example.IncognitoCampus.Services;



import com.example.IncognitoCampus.DTOS.LoginRequest;
import com.example.IncognitoCampus.DTOS.RegisterRequest;
import com.example.IncognitoCampus.Models.Room;
import com.example.IncognitoCampus.Models.User;
import com.example.IncognitoCampus.Repos.RoomRepository;
import com.example.IncognitoCampus.Repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    private static final String[] adjectives = {

            "Brave", "Silent", "Mighty", "Blue", "Curious",

            "Swift", "Wise", "Mystic", "Crimson", "Golden",

            "Fierce", "Gentle", "Hidden", "Lone", "Shadow",

            "Vivid", "Calm", "Electric", "Whispering", "Ancient"

    };

    private static final String[] animals = {

            "Tiger", "Falcon", "Otter", "Panther", "Wolf",

            "Bear", "Eagle", "Fox", "Lion", "Shark",

            "Cobra", "Raven", "Stag", "Lynx", "Badger",

            "Dolphin", "Hawk", "Koala", "Penguin", "Jaguar"

    };


    public String unfollowRoom(String userId, String roomId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getUserRooms().removeIf(room -> room.getId().equals(roomId));
        userRepository.save(user);
        return "Unfollowed successfully";
    }


    public static String generate() {

        Random rand = new Random();

        return adjectives[rand.nextInt(adjectives.length)] +

                animals[rand.nextInt(animals.length)] +

                rand.nextInt(1000);

    }


    public String registerUser(com.example.IncognitoCampus.DTOS.RegisterRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return "Email already registered";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCollege(request.getCollege());
        user.setBranch(request.getBranch());

        String displayName = generate();
        user.setDisplayName(displayName);

        userRepository.save(user);
        return displayName;
    }

    public User loginUser(com.example.IncognitoCampus.DTOS.LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) throw new RuntimeException("UserNotExist:  Register first");

        User user = userOpt.get();
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Incorrect password/email");
        }

        return user;
    }


    public String followRoom(String userId, String roomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        // Avoid duplicate rooms


        boolean alreadyFollowed = user.getUserRooms().stream()
                .anyMatch(r -> r.getId().equals(roomId));

        if (!alreadyFollowed) {
            user.getUserRooms().add(room);
            userRepository.save(user);
        }

        return "Room added successfully";
    }


    public List<Room> getUserRooms(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> ids = user.getUserRooms().stream()
                .map(Room::getId)
                .toList();

        List<Room> existing = roomRepository.findAllById(ids);

        if (existing.size() != ids.size()) {
            user.setUserRooms(new ArrayList<>(existing));
            userRepository.save(user);
        }

        return existing;
    }





}
