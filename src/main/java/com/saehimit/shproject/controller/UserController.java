package com.saehimit.shproject.controller;

import com.saehimit.shproject.dto.UserDto;
import com.saehimit.shproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password,  RedirectAttributes redirectAttributes) {
        try {
            if (userService.authenticate(userId, password)) {
                return "redirect:/main"; // 로그인 성공 시 메인 페이지로 이동
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "login"; // 로그인 실패 시 다시 로그인 페이지로
    }

    //사용자 정보등록

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/main"; // 등록 후 메인 페이지로 리다이렉트
    }



    // 사용자정보조회 (get)
    @GetMapping("/user/search")
    public String renderSearchPage(Model model) {
        // 빈 사용자 리스트를 초기화 (검색 결과 없이 페이지 렌더링)
        model.addAttribute("users", null);
        return "user_search"; // 검색 페이지
    }
    
    //사용자 정보 조회 (post)
    @PostMapping("/user/search")
    public String searchUsers(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String branch,
            Model model) {

        // 검색 조건 확인z
        if ((userId == null || userId.isEmpty()) &&
                (username == null || username.isEmpty()) &&
                (branch == null || branch.isEmpty())) {
            model.addAttribute("users", Collections.emptyList());
        } else {
            // 검색 조건에 따라 결과 조회
            List<UserDto> users = userService.searchUsers(userId, username, branch);
            model.addAttribute("users", users);
        }

        return "user_search";
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    public UserDto getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/check/userId")
    @ResponseBody
    public Map<String, Boolean> checkUserId(@RequestParam String userId) {
        boolean exists = userService.isUserIdExists(userId);
        return Collections.singletonMap("exists", exists);
    }

    @GetMapping("/check/email")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        boolean exists = userService.isEmailExists(email);
        return Collections.singletonMap("exists", exists);
    }


    @GetMapping("/check/phone")
    @ResponseBody
    public Map<String, Boolean> checkPhone(@RequestParam String phoneNumber) {
        boolean exists = userService.isPhoneExists(phoneNumber);
        return Collections.singletonMap("exists", exists);
    }


    //사용자 정보 수정
    @PostMapping("/user/modify")
    public String modifyUser(@ModelAttribute UserDto userDto,
                             @RequestParam(required = false) boolean resetLoginFail,
                             @AuthenticationPrincipal UserDetails currentUser, // 현재 로그인 사용자 정보
                             RedirectAttributes redirectAttributes) {
        try {
            // 현재 사용자 권한 확인
            if (!currentUser.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                throw new SecurityException("관리자 권한이 필요합니다.");
            }

            // 수정일시와 수정자를 설정
            userDto.setModifiedAt(LocalDate.now());
            userDto.setModifiedBy(currentUser.getUsername());

            // 서비스 호출
            userService.updateUser(userDto, resetLoginFail, currentUser.getUsername());

            redirectAttributes.addFlashAttribute("message", "수정 성공");
            return "redirect:/user/search";
        } catch (SecurityException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/user/search";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "수정 실패: " + e.getMessage());
            return "redirect:/user/search";
        }
    }











}


