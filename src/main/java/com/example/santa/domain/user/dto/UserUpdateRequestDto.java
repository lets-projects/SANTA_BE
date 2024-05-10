package com.example.santa.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {
    @Schema(description = "테스트 값", example = "귀요미산악꾼")
    private String nickname;
    @Schema(description = "테스트 값", example = "엘리스")
    private String name;
    @Schema(description = "테스트 값", example = "01023234545")
    private String phoneNumber;
    @Size(max = 1000, message = "이미지명이 너무 깁니다(한글)")
    private String image;
    private MultipartFile imageFile;
}

