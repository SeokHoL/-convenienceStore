package com.saehimit.shproject.dto;

import com.saehimit.shproject.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String confirmPassword; // 비밀번호 확인
    private String role; // "ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"
    private String email;
    private String branch;
    private String phoneNumber;
    private LocalDate joinDate;
    private LocalDate leaveDate;
    private LocalDate modifiedAt;
    private String modifiedBy;
    private int loginFailCount;


    // UserDto.java
    public UserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.branch = user.getBranch();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.joinDate = user.getJoinDate();
        this.leaveDate = user.getLeaveDate();
        this.loginFailCount = user.getLoginFailCount();
        this.modifiedAt = user.getModifiedAt(); // 추가
        this.modifiedBy = user.getModifiedBy(); // 추가
    }

}
