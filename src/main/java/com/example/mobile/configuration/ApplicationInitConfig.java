package com.example.mobile.configuration;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.entity.Role;
import com.example.mobile.entity.User;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Role role = roleRepository.findByRoleName(RolePlay.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Role ADMIN không tồn tại"));

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role(role)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin123, Please change it!.");
            }
        };
    }
}
