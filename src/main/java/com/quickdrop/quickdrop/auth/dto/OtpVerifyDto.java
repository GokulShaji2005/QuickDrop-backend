package com.quickdrop.quickdrop.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class OtpVerifyDto {

    @NotBlank(message = "Mobile number must not be blank")
    private String mobile;

    @NotBlank(message = "OTP must not be blank")
    private String otpCode;

    public OtpVerifyDto() {
    }

    public OtpVerifyDto(String mobile, String otpCode) {
        this.mobile = mobile;
        this.otpCode = otpCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}

