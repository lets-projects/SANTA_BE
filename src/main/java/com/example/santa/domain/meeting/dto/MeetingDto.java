package com.example.santa.domain.meeting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Long meetingId;
    private Long leaderId;
    private String userEmail;
    @NotBlank(message = "모임 이름을 입력하세요.")
    private String meetingName;
    private String categoryName;
    private String mountainName;
    @NotBlank(message = "상세 설명을 입력하세요.")
    private String description;
    @NotNull(message = "인원 수를 입력하세요.")
    private int headcount;
    @NotNull(message = "날짜를 입력하세요.")
    private LocalDate date;
    private List<String> tags;
    private String image;
    private MultipartFile imageFile;
    // 참가자 정보는 모임 상세 조회 시에만 사용
    private List<ParticipantDto> participants;




}
