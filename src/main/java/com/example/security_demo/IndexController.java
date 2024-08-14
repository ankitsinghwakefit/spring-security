package com.example.security_demo;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "welcome to API with security";
    }

    @GetMapping("/about")
    public String about(HttpServletRequest request) {

        return "This is about security" + request.getSession().getId();
    }

}
