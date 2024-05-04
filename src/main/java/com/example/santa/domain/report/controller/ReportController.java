package com.example.santa.domain.report.controller;

import com.example.santa.domain.report.dto.ReportRequestDto;
import com.example.santa.domain.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping
    public ResponseEntity<?> reporting(@AuthenticationPrincipal String email, @RequestBody ReportRequestDto reportRequestDto) {

        return ResponseEntity.ok(reportService.reporting(email, reportRequestDto));
    }
    @Operation(summary = "*관리자* 신고 목록 조회 기능", description = "신고 목록 조회 기능")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getReports(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(reportService.getReports(pageRequest));
    }

    @Operation(summary = "*관리자* 신고 목록 삭제 기능", description = "신고 목록 조회 기능")
    @DeleteMapping("{reportId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteReport(@RequestParam(name = "reportId") Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
}
