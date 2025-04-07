package com.example.shoppingmall.controller;

import com.example.shoppingmall.entity.User;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // 회원가입 폼 페이지 반환
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 회원가입 처리 후 상품 목록으로 리다이렉트
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/products"; // 회원가입 후 상품 목록으로 이동
    }
}
