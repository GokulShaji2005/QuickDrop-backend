package com.quickdrop.quickdrop.auth.dto;

public class AuthResponseDto {

    private String accessToken;

    public AuthResponseDto() {

    }

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
