package com.saehimit.shproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 ID
    private Long uid; // 식별자

    @Column(nullable = false, length = 100, unique = true)
    private String userId; // 사용자가 입력하는 아이디


    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false,length = 100)
    private String branch;

    @Column(nullable = false)
    private String phoneNumber;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String role = "ROLE_USER"; // 기본값: 일반 사용자

    // 객체 생성 시 자동으로 createdAt 값 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public User(String userId, String username, String password, String email, String branch, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.branch = branch;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now(); // Builder를 통한 객체 생성 시에도 createdAt 설정
    }
}