package com.saehimit.shproject.controller.UserManagement;

import com.saehimit.shproject.entity.User;
import com.saehimit.shproject.entity.UserManagement.UserDetails;
import com.saehimit.shproject.service.UserManagement.UserManagementService;
import com.saehimit.shproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserManagementController {

    private final UserManagementService userManagementService;

    // 사용자 정보 관리 페이지
    @GetMapping("/user-management")
    public String userManagementPage(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String branch,
            Model model) {
        List<UserDetails> users = userManagementService.searchUsers(userId, username, branch);
        model.addAttribute("users", users);
        return "user-management";
    }
}