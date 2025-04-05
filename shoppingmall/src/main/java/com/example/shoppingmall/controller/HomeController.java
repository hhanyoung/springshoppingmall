package com.example.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // 홈 페이지 (index.html)
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 로그인 페이지 (login.html)
    }
}
