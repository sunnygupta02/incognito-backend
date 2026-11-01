package com.example.IncognitoCampus.Repos;




import com.example.IncognitoCampus.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String e);
}

