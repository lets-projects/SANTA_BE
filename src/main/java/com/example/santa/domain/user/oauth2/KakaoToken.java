package com.example.santa.domain.user.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoToken {
    private String tokenType;
    @JsonProperty("access_token")
    private String accessToken;
    private String refreshToken;
    private int expiresIn;
}
