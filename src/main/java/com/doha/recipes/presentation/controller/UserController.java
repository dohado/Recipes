package com.doha.recipes.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.doha.recipes.business.model.User;
import com.doha.recipes.business.service.UserService;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Map<String, Long> registerUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return Map.of(savedUser.getEmail(), savedUser.getId());
    }

}
