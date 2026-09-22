package com.ebs.biocrop.service.impl;

import com.ebs.biocrop.dto.request.OtpVerifyRequest;
import com.ebs.biocrop.dto.response.AuthResponse;
import com.ebs.biocrop.entity.User;
import com.ebs.biocrop.entity.enums.UserRole;
import com.ebs.biocrop.repository.UserRepository;
import com.ebs.biocrop.security.jwt.JwtTokenProvider;
import com.ebs.biocrop.service.AuthService;
import com.ebs.biocrop.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final OtpService otpService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            OtpService otpService,
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider) {
        this.otpService = otpService;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse verifyOtpAndLogin(OtpVerifyRequest request) {
        String phoneNumber = request.getPhoneNumber().trim();

        // 1. Verify the provided OTP (checked against in-memory store)
        otpService.verifyOtp(phoneNumber, request.getOtp());

        // 2. Lookup or provision user in MongoDB (default role: ROLE_CUSTOMER, passwordless)
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseGet(() -> {
            log.info("First-time login: Auto-registering new user in 'users' for phone: {} with role: {}", phoneNumber, UserRole.ROLE_CUSTOMER);
            User newUser = new User(phoneNumber, UserRole.ROLE_CUSTOMER);
            return userRepository.save(newUser);
        });

        // 3. Issue JWT Access & Refresh Tokens
        String roleName = user.getRole().name();
        String accessToken = jwtTokenProvider.generateAccessToken(user.getPhoneNumber(), user.getId(), roleName);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getPhoneNumber(), user.getId());

        log.info("User [{}] authenticated successfully with role [{}]. Issued JWT token.", user.getPhoneNumber(), roleName);

        return new AuthResponse(
                accessToken,
                refreshToken,
                jwtTokenProvider.getExpirationMs(),
                user.getId(),
                user.getPhoneNumber(),
                roleName
        );
    }
}
