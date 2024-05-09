package com.example.santa.domain.mountain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MountainResponseDto {
    private Long Id;
    private String name;
    private String location;
    private double height;
    private String description;
    private String point;
    private String transportation;
    private double longitude;
    private double latitude;
}
