package com.example.santa.domain.challege.mapper;

import com.example.santa.domain.challege.dto.ChallengeResponseDto;
import com.example.santa.domain.challege.entity.Challenge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {

    ChallengeResponseDto entityToDto(Challenge challenge);
}
