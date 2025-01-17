package com.saehimit.shproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid; // 식별자

    @Column(name = "user_id", nullable = false, length = 100, unique = true)
    private String userId;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private String role; // 권한 ("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER")

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String branch;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate joinDate; // 입사일자

    @Column(nullable = true)
    private LocalDate leaveDate; // 퇴사일자

    private boolean accountLocked = false;


    @Column(nullable = false)
    private int loginFailCount = 0; // 로그인 실패 횟수

    @Column(nullable = true)
    private LocalDate modifiedAt; // 수정 일시

    @Column(nullable = true, length = 100)
    private String modifiedBy; // 수정자

    @PrePersist
    public void prePersist() {
        if (this.joinDate == null) {
            this.joinDate = LocalDate.now();
        }

    }


}
