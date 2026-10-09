package com.quickdrop.quickdrop.auth.service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.quickdrop.quickdrop.common.exceptions.InvalidInputException;

@Service
public class OtpService {
    private static final long OTP_TTL_MINUTES = 5;
    private static final long COOLDOWN_SECONDS = 60;
    private final RedisTemplate<String, String> redisTemplate;

    public OtpService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String generateOtp() {
        return String.valueOf(100000 + new SecureRandom().nextInt(900000));
    }

    public String saveOtp(String mobile) {
        String cooldownKey = "otp_cooldown:" + mobile;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(cooldownKey))) {
            throw new InvalidInputException("Please wait 60 seconds before requesting another OTP");
        }

        String otp = generateOtp();
        String key = "otp:" + mobile;
        redisTemplate.opsForValue().set(key, otp, OTP_TTL_MINUTES, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(cooldownKey, "1", COOLDOWN_SECONDS, TimeUnit.SECONDS);
        return otp;
    }


    public void verifyOtp(String mobile, String enteredOtp) {
        String key = "otp:" + mobile;
        String storedOtp = redisTemplate.opsForValue().get(key);
        if (storedOtp != null && storedOtp.equals(enteredOtp)) {
            redisTemplate.delete(key);

        } else {
            throw new InvalidInputException("Invalid Otp");
        }

    }
}
