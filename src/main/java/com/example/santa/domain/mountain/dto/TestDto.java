package com.example.santa.domain.mountain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    private String name;
    private String location;
    private double height;
    private double latitude;
    private double longitude;

}
