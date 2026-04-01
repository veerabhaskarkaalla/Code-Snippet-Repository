package com.snippets.controller;

import com.snippets.entity.User;
import com.snippets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        try {
            // Check if username already exists
            if (userService.existsByUsername(user.getUsername())) {
                return "redirect:/register?error=username_exists";
            }
            
            // Check if email already exists
            if (userService.existsByEmail(user.getEmail())) {
                return "redirect:/register?error=email_exists";
            }
            
            // Save user
            userService.createUser(user);
            return "redirect:/login?success=registered";
            
        } catch (Exception e) {
            return "redirect:/register?error=unknown";
        }
    }
    
//    @GetMapping("/dashboard")
//    public String showDashboard() {
//        return "dashboard";
//    }
}