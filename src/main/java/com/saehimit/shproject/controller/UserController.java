package com.saehimit.shproject.controller;


import com.saehimit.shproject.dto.UserDto;
import com.saehimit.shproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto) {
        userService.register(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password, Model model) {

        try {
            if (userService.authenticate(userId, password)) {
                return "redirect:/main"; // 로그인 성공 시 메인 페이지로 이동
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // 한글 메시지를 뷰로 전달
        }
        return "login"; // 로그인 실패 시 다시 로그인 페이지로
    }
}
