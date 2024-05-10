package com.example.santa.domain.report.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {
    @NotBlank(message = "신고 사유를 입력하세요.")
    private String reason;

    private Long reportedParticipantId;

}
