package org.hca.service;

import org.hca.dto.SignupRequest;
import org.hca.models.User;
import org.hca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailOtpService emailOtpService;

    public String registerUser(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use!");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        userRepository.save(user);

        // Send OTP to email
        emailOtpService.generateOtp(request.getEmail());

        return "User registered successfully! Please verify your email.";
    }

    public String verifyEmail(String email, String otp) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "User not found!";
        }

        if (emailOtpService.verifyOtp(email, otp)) {
            User user = userOpt.get();
            user.setVerified(true);
            userRepository.save(user);
            return "Email verified successfully!";
        } else {
            return "Invalid OTP!";
        }
    }
}
