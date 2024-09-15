package com.example.Perseo.services;

import org.springframework.stereotype.Service;

import com.example.Perseo.dtos.AuthResponse;
import com.example.Perseo.dtos.LoginRequest;
import com.example.Perseo.models.User;
import com.example.Perseo.repositories.UserRepository;
import com.example.Perseo.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Aquí debes agregar la lógica de autenticación adecuada sin usar
        // BCryptPasswordEncoder.
        // Por ejemplo, si las contraseñas ya están en texto plano, compara las
        // contraseñas directamente.
        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
