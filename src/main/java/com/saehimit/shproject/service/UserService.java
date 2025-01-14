package com.saehimit.shproject.service;

import com.saehimit.shproject.dto.UserDto;
import com.saehimit.shproject.entity.User;
import com.saehimit.shproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public void register(UserDto userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .branch(userDto.getBranch())
                .build();
        userRepository.save(user);
    }

    public boolean authenticate(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(("아이디가 잘못되었습니다.")));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");

        }

        return true;

    }
}
