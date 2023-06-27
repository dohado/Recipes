package com.doha.recipes.business.service;

import com.doha.recipes.business.model.User;
import com.doha.recipes.exceptions.UserAlreadyRegisteredException;
import com.doha.recipes.exceptions.UserNotFoundException;
import com.doha.recipes.persistence.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User save(User user) {
        if (!userRepo.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } else throw new UserAlreadyRegisteredException(user.getEmail());
    }


}
