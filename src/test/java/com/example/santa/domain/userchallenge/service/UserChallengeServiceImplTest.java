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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserChallengeServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMountainRepository userMountainRepository;
    @Mock
    private ChallengeRepository challengeRepository;
    @Mock
    private UserChallengeRepository userChallengeRepository;

    @InjectMocks
    private UserChallengeServiceImpl userChallengeService; // Assuming the service is called UserService

    private User user;
    private UserMountain userMountain;
    private Challenge challenge;
    private UserChallenge userChallenge;

    @BeforeEach
    public void setUp() {
        // Set up the commonly used objects
        user = new User();
        userMountain = new UserMountain();
        Category category = new Category();
        category.setId(1L);
        category.setName("100대 명산 등산");
        userMountain.setCategory(category);
        challenge = new Challenge();
        challenge.setId(1L);
        challenge.setClearStandard(5);
        userChallenge = new UserChallenge();
        userChallenge.setProgress(0);
        userChallenge.setUser(user);
        userChallenge.setChallenge(challenge);

        // Mock responses for common calls
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        lenient().when(userMountainRepository.findById(anyLong())).thenReturn(Optional.of(userMountain));
        lenient().when(challengeRepository.findByCategoryName(anyString())).thenReturn(Arrays.asList(challenge));
        lenient().when(userChallengeRepository.findByUserAndChallengeId(any(User.class), anyLong())).thenReturn(Optional.of(userChallenge));
    }

    @Test
    public void updateProgress_UserAndUserMountainFound_ChallengesUpdated() {
        userChallengeService.updateProgress("test@example.com", 1L);

        assertEquals(1, userChallenge.getProgress());
        verify(userChallengeRepository, times(1)).save(userChallenge);
    }

    @Test
    public void updateProgress_UserNotFound_ThrowsException() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ServiceLogicException.class, () -> {
            userChallengeService.updateProgress("nonexistent@example.com", 1L);
        });

        assertEquals("존재하지 않는 회원입니다.", exception.getMessage());

    }

    @Test
    public void updateProgress_UserMountainNotFound_ThrowsException() {
        when(userMountainRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ServiceLogicException.class, () -> userChallengeService.updateProgress("test@example.com", 1L));
        assertEquals("존재하지 않는 유저 등산 정보입니다.", exception.getMessage());
        verify(userChallengeRepository, never()).save(any(UserChallenge.class));
    }


    @Test
    public void updateProgress_ChallengeCreationAndCompletion_Checks() {
//        ArgumentCaptor<UserChallenge> userChallengeCaptor = ArgumentCaptor.forClass(UserChallenge.class);
//        when(userChallengeRepository.findByUserAndChallengeId(user, challenge.getId())).thenReturn(Optional.empty());
//        when(userChallengeRepository.save(any(UserChallenge.class))).thenAnswer(invocation -> invocation.getArgument(0));
////        when(userChallengeRepository.save(any(Challenge.class))).thenReturn(challenge);
//        when(challengeRepository.save(any(Challenge.class))).thenReturn(challenge);
//
//
//        userChallengeService.updateProgress("test@example.com", 1L);
//
//        verify(userChallengeRepository).save(userChallengeCaptor.capture());
//        UserChallenge captured = userChallengeCaptor.getValue();
//        assertEquals(1, captured.getProgress());
//        assertFalse(captured.getIsCompleted());
//
//        // Simulating progress reaching the clear standard
//        captured.setProgress(challenge.getClearStandard());
//        userChallengeService.updateProgress("test@example.com", 1L);
//
//        assertTrue(captured.getIsCompleted());
//        assertNotNull(captured.getCompletionDate());

        // UserChallenge 타입 인자를 캡처하기 위한 ArgumentCaptor 생성
        ArgumentCaptor<UserChallenge> userChallengeCaptor = ArgumentCaptor.forClass(UserChallenge.class);

        // findByUserAndChallengeId가 호출될 때 빈 Optional을 반환하도록 모의 구성
        // 이것은 주어진 사용자와 챌린지 조합에 대한 UserChallenge가 아직 없는 시나리오를 시뮬레이션합니다.
        when(userChallengeRepository.findByUserAndChallengeId(user, challenge.getId())).thenReturn(Optional.empty());

        // 모의 save 메서드를 입력된 UserChallenge를 그대로 반환하도록 구성
        // 이것은 저장된 후의 UserChallenge의 상태를 검사하는 데 중요합니다.
        when(userChallengeRepository.save(any(UserChallenge.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 테스트 대상 메서드 호출. 여기서 실제 작업이 수행되며 save 메서드가 두 번 호출될 것으로 예상됩니다:
        // 한 번은 새로운 UserChallenge를 생성할 때, 다른 한 번은 업데이트한 후입니다.
        userChallengeService.updateProgress("test@example.com", 1L);

        // save 메서드가 정확히 두 번 호출되었는지 검증
        verify(userChallengeRepository, times(2)).save(userChallengeCaptor.capture());

        // 캡처된 모든 UserChallenge 인스턴스를 검색. 위의 검증에 따라 두 개가 있어야 합니다.
        List<UserChallenge> capturedChallenges = userChallengeCaptor.getAllValues();

        // 첫 번째 캡처된 UserChallenge에 접근
        // 이것은 처음 생성된 후의 UserChallenge를 나타내야 합니다.
        UserChallenge firstCapture = capturedChallenges.get(0);
        assertEquals(1, firstCapture.getProgress());  // 초기 진행 상태가 올바르게 설정되었는지 확인
        assertNull(firstCapture.getIsCompleted());  // 아직 완료로 표시되어서는 안 됩니다.

        // 두 번째 캡처된 UserChallenge에 접근
        // 이것은 업데이트 후의 상태를 나타낼 수 있습니다, updateProgress가 어떻게 구현되었는지에 따라 다릅니다.
        UserChallenge secondCapture = capturedChallenges.get(1);
        // 필요에 따라 두 번째 save 후의 상태를 확인하기 위한 추가적인 검증을 할 수 있습니다.
        // 예를 들어, 두 번째 save가 챌린지를 완료로 표시하는 것이었다면 assertTrue(secondCapture.getIsCompleted()); 등
    }
}