package com.saehimit.shproject.service.UserManagement;



import com.saehimit.shproject.entity.UserManagement.UserDetails;
import com.saehimit.shproject.repository.UserManagement.UserManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserManagementRepository userManagementRepository;

    public List<UserDetails> searchUsers(String userId, String username, String branch) {
        if (userId != null && !userId.isEmpty()) {
            return userManagementRepository.findByUser_UserIdContaining(userId);
        } else if (username != null && !username.isEmpty()) {
            return userManagementRepository.findByUser_UsernameContaining(username);
        } else if (branch != null && !branch.isEmpty()) {
            return userManagementRepository.findByUser_BranchContaining(branch);
        } else {
            return userManagementRepository.findAll();
        }
    }

}
