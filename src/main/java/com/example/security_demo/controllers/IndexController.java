package com.example.security_demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "Hello user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello admin";
    }

    // either CSRF token or we can restrict the access for cross site requests in
    // application.properties
    @GetMapping("/cerf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

}
