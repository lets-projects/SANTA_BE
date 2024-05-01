package com.example.santa.domain.mountain.service;

import com.example.santa.domain.category.entity.Category;
import com.example.santa.domain.category.repository.CategoryRepository;
import com.example.santa.domain.challege.entity.Challenge;
import com.example.santa.domain.challege.repository.ChallengeRepository;
import com.example.santa.domain.mountain.entity.Mountain;
import com.example.santa.domain.mountain.repository.MountainRepository;
import com.example.santa.domain.user.entity.User;
import com.example.santa.domain.user.repository.UserRepository;
import com.example.santa.domain.userchallenge.entity.UserChallenge;
import com.example.santa.domain.userchallenge.repository.UserChallengeRepository;
import com.example.santa.domain.userchallenge.service.UserChallengeService;
import com.example.santa.domain.usermountain.dto.UserMountainResponseDto;
import com.example.santa.domain.usermountain.entity.UserMountain;
import com.example.santa.domain.usermountain.repository.UserMountainRepository;
import com.example.santa.global.exception.ExceptionCode;
import com.example.santa.global.exception.ServiceLogicException;
import com.example.santa.global.util.mapsturct.UserMountainResponseDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MountainServiceImpl implements MountainService{
//    private final MountainRepository mountainRepository;
//
//    public
}
