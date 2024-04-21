package com.example.santa.domain.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDto {

    @NotNull(message = "카테고리를 입력하세요.")
    private String name;
}
