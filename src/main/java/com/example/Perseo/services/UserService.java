package com.example.Perseo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Perseo.dtos.RegisterRequest;
import com.example.Perseo.models.User;
import com.example.Perseo.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // Asegúrate de encriptar la contraseña aquí
        return userRepository.save(user);
    }

    public User saveUser(RegisterRequest request) {
        return registerUser(request);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
