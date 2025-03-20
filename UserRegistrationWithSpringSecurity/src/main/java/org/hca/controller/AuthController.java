package org.hca.controller;

import org.hca.dto.SignupRequest;
import org.hca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(userService.registerUser(signupRequest));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String email, @RequestParam String otp) {
        return ResponseEntity.ok(userService.verifyEmail(email, otp));
    }
}
