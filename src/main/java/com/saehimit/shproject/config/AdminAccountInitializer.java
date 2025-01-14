package com.saehimit.shproject.config;


import com.saehimit.shproject.entity.User;
import com.saehimit.shproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminAccountInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUserId("admin").isEmpty()) {
                User admin = User.builder()
                        .userId("admin")
                        .username("이대표")
                        .email("admin@example.com")
                        .phoneNumber("01012345678")
                        .password(passwordEncoder.encode("admin123")) // 암호화된 비밀번호
                        .role("ROLE_ADMIN") // 관리자 역할
                        .branch("본사")
                        .build();
                userRepository.save(admin);
                System.out.println("관리자 계정 생성 완료: admin / admin123");
            }
        };
    }
}