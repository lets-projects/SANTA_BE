package com.example.santa.domain.userchallenge.service;

import com.example.santa.domain.category.entity.Category;
import com.example.santa.domain.category.repository.CategoryRepository;
import com.example.santa.domain.challege.entity.Challenge;
import com.example.santa.domain.challege.repository.ChallengeRepository;
import com.example.santa.domain.meeting.entity.Meeting;
import com.example.santa.domain.meeting.repository.MeetingRepository;
import com.example.santa.domain.user.entity.User;
import com.example.santa.domain.user.repository.UserRepository;
import com.example.santa.domain.userchallenge.entity.UserChallenge;
import com.example.santa.domain.userchallenge.repository.UserChallengeRepository;
import com.example.santa.domain.usermountain.entity.UserMountain;
import com.example.santa.domain.usermountain.repository.UserMountainRepository;
import com.example.santa.global.exception.ExceptionCode;
import com.example.santa.global.exception.ServiceLogicException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class UserChallengeServiceImpl implements UserChallengeService{
    private final UserChallengeRepository userChallengeRepository;
    private final ChallengeRepository challengeRepository;
    private final UserMountainRepository userMountainRepository;

    private final MeetingRepository meetingRepository;
    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserChallengeServiceImpl(UserChallengeRepository userChallengeRepository
            ,ChallengeRepository challengeRepository
            ,CategoryRepository categoryRepository
            ,UserRepository userRepository
            ,UserMountainRepository userMountainRepository
            ,MeetingRepository meetingRepository){
        this.userChallengeRepository = userChallengeRepository;
        this.challengeRepository =challengeRepository;
        this.categoryRepository = categoryRepository;
        this.meetingRepository = meetingRepository;
        this.userMountainRepository = userMountainRepository;
        this.userRepository = userRepository;
    }

    // 산 등반 인증 후 UserChallenge 업데이트 메소드
//    public void updateProgressAndCheckCompletion(String email, Long challengeId) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자를 찾을 수 없습니다."));
//        Challenge challenge = challengeRepository.findById(challengeId)
//                .orElseThrow(() -> new IllegalArgumentException("챌린지를 찾을 수 없습니다."));
//
//        UserChallenge userChallenge = userChallengeRepository.findByUserEmailAndChallengeId(user.getEmail(), challengeId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 챌린지를 찾을 수 없습니다."));
//
//        // progress를 1 증가
//        userChallenge.setProgress(userChallenge.getProgress() + 1);
//
//        // clearStandard와 progress가 일치하면 챌린지 성공 처리
//        if (userChallenge.getProgress().equals(userChallenge.getChallenge().getClearStandard())) {
//            userChallenge.setIsCompleted(true);
//            userChallenge.setCompletionDate(new Date()); // 현재 날짜로 성공 일자 설정
//        }
//
//        userChallengeRepository.save(userChallenge);
//    }

//    // UserChallengeService 클래스 내부에 추가
//    public void updateUserChallengeProgress(User user, Category category) {
//        List<UserChallenge> challenges = userChallengeRepository.findByUserAndChallengeCategory(user, category);
//
//        for (UserChallenge challenge : challenges) {
//            // progress가 null일 경우를 대비해 초기값 설정
//            int currentProgress = (challenge.getProgress() == null) ? 0 : challenge.getProgress();
//            challenge.setProgress(currentProgress + 1);  // 진행 상태 증가
//
//            // 진행 상태가 clearStandard와 같거나 크면 도전 성공 처리
//            if (challenge.getProgress() >= challenge.getChallenge().getClearStandard()) {
//                challenge.setIsCompleted(true);
//                challenge.setCompletionDate(new LocalDate());  // 현재 시간으로 성공 일자 설정
//            }
//
//            userChallengeRepository.save(challenge);  // 업데이트된 UserChallenge 저장
////            log.info("mountain {}", mountain);
//
//        }
//    }

    @Override
    public void updateProgress(String email, Long userMountainId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.USER_NOT_FOUND));
        UserMountain userMountain = userMountainRepository.findById(userMountainId)
                .orElseThrow(() -> new IllegalArgumentException("유저 마운틴을 찾을 수 없습니다."));
        Category userMountainCategory = userMountain.getCategory();

        List<Challenge> challenges = challengeRepository.findByCategoryName(userMountainCategory.getName());


        for (Challenge challenge : challenges) {
            UserChallenge userChallenge = userChallengeRepository.findByUserAndChallengeId(user, challenge.getId())
                    .orElseGet(() -> {
                        // 새로운 UserChallenge 생성
                        UserChallenge newUserChallenge = UserChallenge.builder()
                                .user(user)
                                .challenge(challenge)
                                .progress(0) // 초기 진행 상태는 0
                                .build();
                        return userChallengeRepository.save(newUserChallenge);
                    });
            log.info("userChallenge {}", userChallenge);

            // progress 증가
            userChallenge.setProgress(userChallenge.getProgress() + 1);

            // clearStandard와 progress가 일치하면 성공 처리
            if (userChallenge.getProgress().equals(challenge.getClearStandard())) {
                userChallenge.setIsCompleted(true);
                userChallenge.setCompletionDate(LocalDate.now()); // 성공일자는 현재 날짜로 설정
            }

            userChallengeRepository.save(userChallenge);
        }

    }

    @Transactional
    public void updateUserChallengeOnMeetingJoin(Long meetingId,String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.USER_NOT_FOUND));
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.MEETING_NOT_FOUND));
        Category meetingCategory = meeting.getCategory();

        // 사용자와 같은 카테고리의 챌린지 찾기
        List<Challenge> challenges = challengeRepository.findByCategoryName(meetingCategory.getName());

        for (Challenge challenge : challenges) {
            UserChallenge userChallenge = userChallengeRepository.findByUserAndChallengeId(user, challenge.getId())
                    .orElseGet(() -> {
                        // 새로운 UserChallenge 생성
                        UserChallenge newUserChallenge = UserChallenge.builder()
                                .user(user)
                                .challenge(challenge)
                                .progress(0) // 초기 진행 상태는 0
                                .build();
                        return userChallengeRepository.save(newUserChallenge);
                    });

            // progress 증가
            userChallenge.setProgress(userChallenge.getProgress() + 1);

            // clearStandard와 progress가 일치하면 성공 처리
            if (userChallenge.getProgress().equals(challenge.getClearStandard())) {
                userChallenge.setIsCompleted(true);
                userChallenge.setCompletionDate(LocalDate.now()); // 성공일자는 현재 날짜로 설정
            }

            userChallengeRepository.save(userChallenge);
        }
    }
}
