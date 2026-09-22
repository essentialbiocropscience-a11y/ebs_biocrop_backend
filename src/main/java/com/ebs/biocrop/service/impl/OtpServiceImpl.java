package com.ebs.biocrop.service.impl;

import com.ebs.biocrop.dto.request.OtpSendRequest;
import com.ebs.biocrop.dto.response.OtpResponse;
import com.ebs.biocrop.exception.InvalidOtpException;
import com.ebs.biocrop.exception.OtpCooldownException;
import com.ebs.biocrop.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class OtpServiceImpl implements OtpService {

    private static final Logger log = LoggerFactory.getLogger(OtpServiceImpl.class);
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    // Zero database storage: Thread-safe in-memory cache for high speed & zero extra cloud cost
    private final ConcurrentMap<String, OtpEntry> otpCache = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    @Value("${app.otp.length:6}")
    private int otpLength;

    @Value("${app.otp.expiration-minutes:5}")
    private int expirationMinutes;

    @Value("${app.otp.cooldown-seconds:60}")
    private int cooldownSeconds;

    @Value("${app.otp.max-attempts:3}")
    private int maxAttempts;

    public OtpServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OtpResponse generateAndSendOtp(OtpSendRequest request) {
        String phoneNumber = request.getPhoneNumber().trim();

        // 1. Check cooldown (prevent spamming OTP requests)
        OtpEntry existingEntry = otpCache.get(phoneNumber);
        if (existingEntry != null && !existingEntry.isExpired()) {
            LocalDateTime cooldownThreshold = LocalDateTime.now().minusSeconds(cooldownSeconds);
            if (existingEntry.getCreatedAt().isAfter(cooldownThreshold)) {
                long elapsed = Duration.between(existingEntry.getCreatedAt(), LocalDateTime.now()).getSeconds();
                long remaining = Math.max(1, cooldownSeconds - elapsed);
                throw new OtpCooldownException(
                        "Please wait " + remaining + " seconds before requesting another OTP",
                        remaining
                );
            }
        }

        // 2. Generate secure numeric 6-digit OTP
        String plainOtp = generateNumericOtp(otpLength);

        // 3. Hash OTP before in-memory storage for tamper resistance
        String hashedOtp = passwordEncoder.encode(plainOtp);
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(expirationMinutes);

        OtpEntry newEntry = new OtpEntry(hashedOtp, expiryTime);
        otpCache.put(phoneNumber, newEntry);

        // 4. Log OTP in dev/test mode for seamless Postman testing
        log.info("=================================================================");
        log.info("🔐 [DEV/TEST] GENERATED OTP FOR PHONE NUMBER [{}]: [{}]", phoneNumber, plainOtp);
        log.info("⏰ Valid for {} minutes | Max attempts: {}", expirationMinutes, maxAttempts);
        log.info("=================================================================");

        return new OtpResponse(
                maskPhoneNumber(phoneNumber),
                "OTP_SENT",
                cooldownSeconds,
                expirationMinutes,
                "OTP has been successfully dispatched."
        );
    }

    @Override
    public void verifyOtp(String phoneNumber, String rawOtp) {
        String trimmedPhoneNumber = phoneNumber.trim();

        OtpEntry otpEntry = otpCache.get(trimmedPhoneNumber);
        if (otpEntry == null) {
            throw new InvalidOtpException("No active OTP request found for phone number: " + trimmedPhoneNumber);
        }

        // 1. Check expiration
        if (otpEntry.isExpired()) {
            otpCache.remove(trimmedPhoneNumber);
            throw new InvalidOtpException("The OTP has expired. Please request a new one.");
        }

        // 2. Check maximum attempts
        if (otpEntry.getAttempts() >= maxAttempts) {
            otpCache.remove(trimmedPhoneNumber);
            throw new InvalidOtpException("Maximum verification attempts exceeded. Please request a new OTP.");
        }

        // 3. Increment attempts
        otpEntry.incrementAttempts();

        // 4. Verify hash
        boolean matches = passwordEncoder.matches(rawOtp.trim(), otpEntry.getHashedOtp());
        if (!matches) {
            int remaining = maxAttempts - otpEntry.getAttempts();
            if (remaining <= 0) {
                otpCache.remove(trimmedPhoneNumber);
                throw new InvalidOtpException("Invalid OTP. Maximum attempts reached. Please request a new OTP.");
            }
            throw new InvalidOtpException("Invalid OTP entered. " + remaining + " attempt(s) remaining.");
        }

        // 5. Successful verification: remove OTP from in-memory cache to prevent replay
        otpCache.remove(trimmedPhoneNumber);
        log.info("✅ Successfully verified OTP for phone number: {}", trimmedPhoneNumber);
    }

    private String generateNumericOtp(int length) {
        int bound = (int) Math.pow(10, length);
        int floor = (int) Math.pow(10, length - 1);
        int code = floor + SECURE_RANDOM.nextInt(bound - floor);
        return String.valueOf(code);
    }

    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return "****";
        }
        int len = phoneNumber.length();
        return phoneNumber.substring(0, 2) + "*".repeat(len - 4) + phoneNumber.substring(len - 2);
    }

    // In-memory OTP model
    private static class OtpEntry {
        private final String hashedOtp;
        private final LocalDateTime expiryTime;
        private final LocalDateTime createdAt;
        private int attempts;

        public OtpEntry(String hashedOtp, LocalDateTime expiryTime) {
            this.hashedOtp = hashedOtp;
            this.expiryTime = expiryTime;
            this.createdAt = LocalDateTime.now();
            this.attempts = 0;
        }

        public String getHashedOtp() {
            return hashedOtp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public int getAttempts() {
            return attempts;
        }

        public void incrementAttempts() {
            this.attempts++;
        }

        public boolean isExpired() {
            return LocalDateTime.now().isAfter(this.expiryTime);
        }
    }
}
