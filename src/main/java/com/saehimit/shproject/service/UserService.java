package com.saehimit.shproject.service;

import com.saehimit.shproject.dto.UserDto;
import com.saehimit.shproject.entity.User;
import com.saehimit.shproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean authenticate(String userId, String password) {
        // 아이디가 존재하지 않을 경우 예외 발생
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 잘못되었습니다."));

        // 계정 잠금 여부 확인
        if (user.getLoginFailCount() >= 5) {
            throw new IllegalStateException("계정이 잠겼습니다. 관리자에게 문의하세요.");
        }

        // 비밀번호가 일치하지 않을 경우 실패 횟수 증가 및 예외 발생
        if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setLoginFailCount(user.getLoginFailCount() + 1); // 실패 횟수 증가
            userRepository.save(user); // 변경 사항 저장
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        // 로그인 성공 시 실패 횟수 초기화
        user.setLoginFailCount(0);
        userRepository.save(user);

        return true;
    }


        public void saveUser(UserDto userDto) {


            User user = User.builder()
                    .userId(userDto.getUserId())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .email(userDto.getEmail())
                    .branch(userDto.getBranch())
                    .phoneNumber(userDto.getPhoneNumber())
                    .role(userDto.getRole())
                    .joinDate(userDto.getJoinDate())
                    .leaveDate(userDto.getLeaveDate())
                    .build();
            userRepository.save(user);
        }

    public List<UserDto> searchUsers(String userId, String username, String branch) {
        List<User> users = userRepository.findUsersByCriteria(userId, username, branch);

        return users.stream()
                .map(user -> UserDto.builder()
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .role(user.getRole())
                        .branch(user.getBranch())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .joinDate(user.getJoinDate())
                        .leaveDate(user.getLeaveDate())
                        .modifiedAt(user.getModifiedAt()) // 추가
                        .modifiedBy(user.getModifiedBy()) // 추가
                        .build())
                .collect(Collectors.toList());
    }


    public void updateUser(UserDto userDto, boolean resetLoginFail, String modifiedBy) {
        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 엔티티에 데이터 반영
        user.setUsername(userDto.getUsername());
        user.setRole(userDto.getRole());
        user.setBranch(userDto.getBranch());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setJoinDate(userDto.getJoinDate());
        user.setLeaveDate(userDto.getLeaveDate());

        // 수정일시를 현재 날짜로 설정
        user.setModifiedAt(LocalDate.now());
        user.setModifiedBy(modifiedBy);

        if (resetLoginFail) {
            user.setLoginFailCount(0);
        }

        userRepository.save(user);
    }




    public UserDto getUserById(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserDto(user);
    }


    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    public boolean isPhoneExists(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber); // 핸드폰 번호 중복 여부 확인
    }

    public boolean isUserIdExists(String userId) {
        return userRepository.findByUserId(userId).isPresent();
    }



}
