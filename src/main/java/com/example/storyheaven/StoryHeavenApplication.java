package com.example.storyheaven;

import com.example.storyheaven.entity.User;
import com.example.storyheaven.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class StoryHeavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoryHeavenApplication.class, args);
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            if (userRepository.findByEmail("admin@storyheaven.kz").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@storyheaven.kz")
                        .password(passwordEncoder.encode("admin"))
                        .role("ROLE_ADMIN")
                        .build();
                userRepository.save(admin);
                System.out.println("Администратор создан: admin@storyheaven.kz / admin");
            }
        };
    }
}
