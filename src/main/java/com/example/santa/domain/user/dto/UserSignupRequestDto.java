package com.example.santa.domain.user.dto;

import com.example.santa.domain.user.entity.SocialType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSignupRequestDto {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    @NotBlank
    private String phoneNumber;
    private SocialType socialType;
    private String socialId;
}
