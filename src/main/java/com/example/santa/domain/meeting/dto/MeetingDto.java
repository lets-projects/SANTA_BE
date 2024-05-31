package com.example.santa.domain.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingDto {

    @Schema(description = "테스트 값", example = "산악회")
    @NotBlank(message = "모임 이름을 입력하세요.")
    private String meetingName;
    @Schema(description = "테스트 값", example = "등산")
    private String categoryName;
    @Schema(description = "테스트 값", example = "북한산")
    private String mountainName;
    @Schema(description = "테스트 값", example = "북한산 등산 후 식사")
    @NotBlank(message = "상세 설명을 입력하세요.")
    private String description;
    @Schema(description = "테스트 값", example = "15")
    @NotNull(message = "인원 수를 입력하세요.")
    private int headcount;
    @Schema(description = "테스트 값", example = "2024-05-20")
    @NotNull(message = "날짜를 입력하세요.")
    private LocalDate date;
    @Schema(description = "테스트 값", example = "[\"산행\", \"등산모임\"]")
    @NotNull(message = "한 개 이상의 태그를 입력해주세요.")
    private List<String> tags;
    @Size(max = 1000, message = "이미지명이 너무 깁니다(한글)")
    private String image;
    private MultipartFile imageFile;


}
