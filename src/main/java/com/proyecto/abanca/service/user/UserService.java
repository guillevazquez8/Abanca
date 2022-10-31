package com.proyecto.abanca.service.user;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.user.User;
import com.proyecto.abanca.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BadRequestException("Entered user ID doesn't exist.");
        }
        return user.get();
    }

}