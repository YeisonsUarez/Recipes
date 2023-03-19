package com.example.recipes.auth;


import com.example.recipes.commos.Role;
import com.example.recipes.entities.Consumer;
import com.example.recipes.repositories.ConsumerRepository;
import com.example.recipes.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final ConsumerRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var consumer = Consumer
                .builder()
                .name(request.getName())
                .channel(request.getChannel())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Role.CONSUMER)
                .build();
        repository.save(consumer);

        var jwtToken = jwtService.generateToken(consumer);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var consumer = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(consumer);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
