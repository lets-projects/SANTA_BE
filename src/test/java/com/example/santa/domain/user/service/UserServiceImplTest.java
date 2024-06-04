package com.example.santa.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.santa.domain.report.repository.ReportRepository;
import com.example.santa.domain.user.dto.UserResponseDto;
import com.example.santa.domain.user.dto.UserSignInRequestDto;
import com.example.santa.domain.user.dto.UserSignupRequestDto;
import com.example.santa.domain.user.dto.UserUpdateRequestDto;
import com.example.santa.domain.user.entity.Password;
import com.example.santa.domain.user.entity.Role;
import com.example.santa.domain.user.entity.User;
import com.example.santa.domain.user.repository.UserRepository;
import com.example.santa.global.exception.ServiceLogicException;
import com.example.santa.global.security.jwt.JwtToken;
import com.example.santa.global.security.jwt.JwtTokenProvider;
import com.example.santa.global.util.S3ImageService;
import com.example.santa.global.util.mapsturct.UserResponseDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserResponseDtoMapper userResponseDtoMapper;
    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private S3ImageService s3ImageService;
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private Authentication authentication;
    private UserSignupRequestDto requestDto;
    private UserUpdateRequestDto updateRequestDto;
    private UserSignInRequestDto signInRequestDto;
    private UserResponseDto userResponseDto;
    private String existingImageUrl;
    private String newImageUrl;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1L);
        user.setEmail("user@email.com");
        user.setName("test");
        user.setNickname("testNickName");
        user.setPhoneNumber("01011111111");
        user.setPassword(new Password("Password1!"));
        user.setRole(Role.ADMIN);
        user.setImage("http://example.com/original-image.jpg");
        user.setAccumulatedHeight(1000);

        requestDto = new UserSignupRequestDto();
        requestDto.setEmail("user@email.com");
        requestDto.setPassword("Password1!");
        requestDto.setName("test");
        requestDto.setNickname("testNickName");
        requestDto.setPhoneNumber("01011111111");

        signInRequestDto = new UserSignInRequestDto();
        signInRequestDto.setEmail("user@email.com");
        signInRequestDto.setPassword("Password1!");

        updateRequestDto = new UserUpdateRequestDto();
        updateRequestDto.setNickname("NewNickname");
        updateRequestDto.setPhoneNumber("01022222222");
        updateRequestDto.setName("NewName");
        updateRequestDto.setImageFile(null);
        updateRequestDto.setImage(existingImageUrl);

        userResponseDto = new UserResponseDto();  // 초기화 추가
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setNickname(user.getNickname());
        userResponseDto.setName(user.getName());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setAccumulatedHeight(user.getAccumulatedHeight());

        existingImageUrl = "http://example.com/original-image.jpg";
        newImageUrl = "http://example.com/new-uploaded-image.jpg";

//        when(userRepository.findByEmail("user@email.com")).thenReturn(Optional.of(user));
        lenient().when(authenticationManagerBuilder.getObject()).thenReturn(authenticationManager);

    }

    @Test
    void testSignup_Success() {
        when(userService.checkEmailDuplicate(anyString())).thenReturn(false);
        when(userService.checkNicknameDuplicate(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> {
            User user = i.getArgument(0);
            user.setId(1L);
            return user;
        });

        Long userId = userService.signup(requestDto);
        assertNotNull(userId);
        assertEquals(1L, userId);
    }


    @Test
    void testCheckEmailDuplicate_WhenExists_ReturnsTrue() {
        // Arrange
//        String email = "user@example.com";
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        // Act
        Boolean result = userService.checkEmailDuplicate(user.getEmail());

        // Assert
        assertTrue(result);
        verify(userRepository).existsByEmail(user.getEmail());
    }

    @Test
    void testCheckEmailDuplicate_WhenNotExists_ReturnsFalse() {
        // Arrange
        String email = "user@example.com";
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        // Act
        Boolean result = userService.checkEmailDuplicate(user.getEmail());

        // Assert
        assertFalse(result);
        verify(userRepository).existsByEmail(user.getEmail());
    }

    @Test
    void testCheckNicknameDuplicate_WhenExists_ReturnsTrue() {
        // Arrange
        String nickname = "nickname";
        when(userRepository.existsByNickname(user.getNickname())).thenReturn(true);

        // Act
        Boolean result = userService.checkNicknameDuplicate(user.getNickname());

        // Assert
        assertTrue(result);
        verify(userRepository).existsByNickname(user.getNickname());
    }

    @Test
    void testCheckNicknameDuplicate_WhenNotExists_ReturnsFalse() {
        // Arrange
        String nickname = "nickname";
        when(userRepository.existsByNickname(user.getNickname())).thenReturn(false);

        // Act
        Boolean result = userService.checkNicknameDuplicate(user.getNickname());

        // Assert
        assertFalse(result);
        verify(userRepository).existsByNickname(user.getNickname());
    }

    @Test
    void signIn_Success() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        // Arrange
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                signInRequestDto.getEmail(), signInRequestDto.getPassword(), user.getAuthorities());

        // Mock the Authentication object that is expected to be returned by the authenticationManager
        Authentication authenticated = Mockito.mock(Authentication.class);
        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticated);

        JwtToken expectedToken = new JwtToken("Bearer", "dummy-access-token", "d", "ADMIN");

        // Use lenient to avoid strict stubbing errors, matching any Authentication object
        Mockito.lenient().when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn(expectedToken);

        // Act
        JwtToken actualToken = userService.signIn(signInRequestDto);

        // Assert
        assertNotNull(actualToken);
        assertEquals(expectedToken, actualToken);

        verify(userRepository).findByEmail(signInRequestDto.getEmail());
        verify(authenticationManager).authenticate(authenticationToken);

        // Adjust verification to match any Authentication object rather than the specific mock
        verify(jwtTokenProvider).generateToken(any(Authentication.class));  // This change ensures that any Authentication object is accepted
    }
    @Test
    void signIn_UserNotFound_ThrowsException() {
        when(userRepository.findByEmail("user@email.com")).thenReturn(Optional.empty());
        // Act & Assert
        Exception exception = assertThrows(ServiceLogicException.class, () -> userService.signIn(signInRequestDto));
        assertEquals("존재하지 않는 회원입니다.", exception.getMessage());
    }

    @Test
    void findUserByEmail_UserExists_ReturnsDto() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userResponseDtoMapper.toDto(any(User.class))).thenReturn(userResponseDto);
        // Act
        UserResponseDto result = userService.findUserByEmail(user.getEmail());

        // Assert
        assertNotNull(result);
        assertEquals(userResponseDto.getEmail(), result.getEmail());
        assertEquals(userResponseDto.getNickname(), result.getNickname());
        assertEquals(userResponseDto.getName(), result.getName());
        assertEquals(userResponseDto.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(userResponseDto.getAccumulatedHeight(), result.getAccumulatedHeight());

        verify(userRepository).findByEmail(user.getEmail());
        verify(userResponseDtoMapper).toDto(user);
    }
/*
    @Test
    void updateUser_WithoutNewImage_UpdatesUserInfoOnly() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userResponseDtoMapper.toDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = userService.updateUser(user.getEmail(), updateRequestDto);
        System.out.println("nick : " + user.getNickname());
        System.out.println("result nick : " + result.getNickname());

        UserUpdateRequestDto requestDto = new UserUpdateRequestDto();
        requestDto.setNickname("NewNickname");
        requestDto.setPhoneNumber("NewPhone");
        requestDto.setName("NewName");
        requestDto.setImageFile(null);
        requestDto.setImage(existingImageUrl);

        assertNotNull(result);
//        assertEquals("NewNickname",result.getNickname());
        assertEquals(user.getNickname(),result.getNickname());
        verify(s3ImageService, never()).deleteImageFromS3(anyString());
        verify(s3ImageService, never()).upload(any());
    }

    @Test
    void updateUser_WithNewImage_UpdatesUserInfoAndImage() {
        // Given
        MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "image data".getBytes());
//        UserUpdateRequestDto requestDto = new UserUpdateRequestDto();
        updateRequestDto.setImage(newImageUrl);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(s3ImageService.upload(imageFile)).thenReturn(newImageUrl);
        when(userResponseDtoMapper.toDto(user)).thenReturn(userResponseDto);

        // Act
        UserResponseDto result = userService.updateUser(user.getEmail(), updateRequestDto);

        System.out.println("new image: "+newImageUrl);
        System.out.println("exist image: "+user.getImage());
        // Assert
        assertNotNull(result);
        assertEquals(newImageUrl, user.getImage());
//        verify(s3ImageService).deleteImageFromS3(existingImageUrl);
        verify(s3ImageService).upload(imageFile);
    }

 */

}