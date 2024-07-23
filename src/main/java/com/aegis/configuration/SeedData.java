package com.aegis.configuration;

import com.aegis.model.User;
import com.aegis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        List<User> list = userRepository.findAll();
        if (list.isEmpty()) {
            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setEmail("safeiridwan06@gmail.com");
            user.setPassword(bCryptPasswordEncoder.encode("1111"));
            userRepository.save(user);
        }
    }
}
