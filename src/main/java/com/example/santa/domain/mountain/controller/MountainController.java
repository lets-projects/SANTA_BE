package com.example.santa.domain.mountain.controller;

import com.example.santa.domain.challege.dto.ChallengeResponseDto;
import com.example.santa.domain.mountain.service.MountainService;
import com.example.santa.domain.usermountain.dto.UserMountainResponseDto;
import com.example.santa.domain.usermountain.dto.UserMountainVerifyRequestDto;
import com.example.santa.domain.usermountain.service.UserMountainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mountains")
public class MountainController {

    private final UserMountainService userMountainService;
    @Operation(summary = "등산 인증 기능(유저 마운틴 등록)", description = "등산 인증 기능(유저 마운틴 등록)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = UserMountainResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "에러", content = @Content(schema = @Schema(implementation = UserMountainResponseDto.class)))})
    @PostMapping("/verify")
    public ResponseEntity<UserMountainResponseDto> createUserMountain(@AuthenticationPrincipal String email, @RequestBody UserMountainVerifyRequestDto request) {
        UserMountainResponseDto userMountain = userMountainService.verifyAndCreateUserMountain(
                request.getLatitude(), request.getLongitude(), request.getClimbDate(), email);
        return ResponseEntity.ok(userMountain);
    }

    @Operation(summary = "등산 인증 기능(유저 마운틴 등록)", description = "등산 인증 기능(유저 마운틴 등록)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = UserMountainResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "에러", content = @Content(schema = @Schema(implementation = UserMountainResponseDto.class)))})
    @PostMapping("/test")
    public ResponseEntity<UserMountainResponseDto> createUserMountain1(@AuthenticationPrincipal String email, @RequestParam double latitude, double longitude, LocalDate climbDate) {

        System.out.println(email);
        UserMountainResponseDto userMountains = userMountainService.verifyAndCreateUserMountain(latitude,longitude,climbDate, email );

//            return new ResponseEntity<>(userMountains, HttpStatus.CREATED);
        return ResponseEntity.ok(userMountains);
    }

    @Operation(summary = "등산 인증 기능(유저 마운틴 등록)", description = "등산 인증 기능(유저 마운틴 등록)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation =UserMountainResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "에러", content = @Content(schema = @Schema(implementation = UserMountainResponseDto.class)))})
    @PostMapping("/test2")
    public ResponseEntity<UserMountainResponseDto> createUserMountain1(@AuthenticationPrincipal String email, @io.swagger.v3.oas.annotations.parameters.RequestBody UserMountainVerifyRequestDto request) {

        System.out.println(email);
        UserMountainResponseDto userMountains = userMountainService.verifyAndCreateUserMountain1(request, email);


//            return new ResponseEntity<>(userMountains, HttpStatus.CREATED);
        return ResponseEntity.ok(userMountains);
    }
}
