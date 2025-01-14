package com.saehimit.shproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String branch;
}
