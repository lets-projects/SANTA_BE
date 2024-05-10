package com.example.santa.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {

    private Long id;

    private String reason;

    private String reportedParticipantName;
    private String reportedParticipantNickname;
    private Long reportedParticipantId;

    private String reporterName;
    private String reporterNickname;
    private Long reporterId;


}
