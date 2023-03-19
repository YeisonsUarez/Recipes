package com.example.recipes.config;

import com.example.recipes.repositories.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ConsumeConfig {

    private final ConsumerRepository consumerRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> consumerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Consumer not found"));
    }
}
