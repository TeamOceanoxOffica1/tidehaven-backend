package com.tidehaven.controller;

import com.tidehaven.model.User;
import com.tidehaven.repository.UserRepository;
import com.tidehaven.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public String register(@RequestBody User req) {
        if (userRepo.findByUsername(req.getUsername()) != null) {
            return "USERNAME_TAKEN";
        }

        userRepo.save(req);
        return "OK";
    }

    @PostMapping("/login")
    public String login(@RequestBody User req) {

        User user = userRepo.findByUsername(req.getUsername());

        if (user == null) return "INVALID";
        if (!user.getPassword().equals(req.getPassword())) return "INVALID";

        return JwtService.generateToken(user.getId());
    }
}