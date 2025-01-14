package com.saehimit.shproject.service;



import com.saehimit.shproject.entity.User;
import com.saehimit.shproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // User 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // 디버깅 로그 추가
        System.out.println("로그인 성공: " + user.getUserId());

        // 권한 설정 (Spring Security가 이해할 수 있는 형식으로)
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole()) // "ROLE_ADMIN"과 같은 형식이어야 함
        );

        // Spring Security에서 제공하는 UserDetails 구현체 반환
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),  // 사용자 ID
                user.getPassword(), // 암호화된 비밀번호
                authorities         // 권한 정보
        );
    }
}