package com.saehimit.shproject.entity.UserManagement;

import com.saehimit.shproject.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uid")
    private User user;

    // branch 필드 대신 User의 branch를 사용
    public String getBranch() {
        return user != null ? user.getBranch() : null;
    }

    @Column
    private String permissionGroup;

    @Column
    private String department;

    @Column
    private String position;

    @Column
    private LocalDateTime joinDate;

    @Column
    private LocalDateTime leaveDate;

    @Column
    private int failedLoginResetCount;

    @Column
    private LocalDateTime lastModified;

    @Column
    private String lastModifiedBy;
}
