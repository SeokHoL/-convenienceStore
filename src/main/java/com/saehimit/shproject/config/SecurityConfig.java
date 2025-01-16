package com.saehimit.shproject.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login",  "/css/**", "/js/**", "/images/**").permitAll() // 공용 리소스 접근 허용
                        .requestMatchers("/main/**").hasAnyRole("USER", "ADMIN","MANAGER")
                        .requestMatchers("/register/**","/user/search").hasAnyRole("ADMIN")// 사용자와 관리자 모두 접근 가능
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지
                        .failureUrl("/login?error=true") // 로그인 실패 시 리다이렉트
                        .defaultSuccessUrl("/main", true) // 성공 시 리다이렉트
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 경로
                        .logoutSuccessUrl("/login") // 로그아웃 성공 시 `/login`으로 이동
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화 (필요 시 활성화)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
