package com.saehimit.shproject.repository.UserManagement;

import com.saehimit.shproject.entity.UserManagement.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserManagementRepository extends JpaRepository<UserDetails, Long> {
    List<UserDetails> findByUser_UserIdContaining(String userId);
    List<UserDetails> findByUser_UsernameContaining(String username);
    List<UserDetails> findByUser_BranchContaining(String branch);
}
