package com.quickdrop.quickdrop.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class OtpRequestDto {
    @NotBlank(message = "Mobile number must not be blank")
    private String mobile;

    public OtpRequestDto() {

    }

    public OtpRequestDto(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
