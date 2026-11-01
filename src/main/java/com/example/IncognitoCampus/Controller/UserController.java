package com.example.IncognitoCampus.Controller;

import com.example.IncognitoCampus.DTOS.LoginRequest;
import com.example.IncognitoCampus.DTOS.LoginResponse;
import com.example.IncognitoCampus.DTOS.RegisterRequest;
import com.example.IncognitoCampus.Models.Room;
import com.example.IncognitoCampus.Models.User;
import com.example.IncognitoCampus.Repos.UserRepository;
import com.example.IncognitoCampus.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        User user=userService.loginUser(request);
        LoginResponse loginRes=new LoginResponse();
        loginRes.setEmail(user.getEmail());
        loginRes.setBranch(user.getBranch());
        loginRes.setDisplayName(user.getDisplayName());
        loginRes.setCollege(user.getCollege());
        loginRes.setId(user.getId());
        return loginRes;
    }

    // Add room to user
    @PostMapping("/{userId}/follow/{roomId}")
    public String followRoom(@PathVariable String userId, @PathVariable String roomId) {
        return userService.followRoom(userId, roomId);
    }

    @DeleteMapping("/{userId}/unfollow/{roomId}")
    public String unfollowRoom(@PathVariable String userId, @PathVariable String roomId) {
        return userService.unfollowRoom(userId, roomId);
    }

    @GetMapping("/{userId}/rooms")
    public List<Room> getUserRooms(@PathVariable String userId) {
        return userService.getUserRooms(userId);
    }

}

