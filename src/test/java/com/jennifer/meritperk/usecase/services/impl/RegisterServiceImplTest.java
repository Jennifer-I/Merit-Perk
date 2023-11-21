package com.jennifer.meritperk.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.*;
import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.SchoolEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.enums.Position;
import com.jennifer.meritperk.domain.entities.enums.Role;
import com.jennifer.meritperk.domain.entities.enums.Status;
import com.jennifer.meritperk.infrastructure.error_handler.EntityAlreadyExistException;
import com.jennifer.meritperk.usecase.payload.request.TeacherRegistrationRequest;
import com.jennifer.meritperk.usecase.services.ConfirmationTokenService;
import com.jennifer.meritperk.utils.CloudinaryService;
import com.jennifer.meritperk.utils.EmailService;
import com.jennifer.meritperk.utils.PayLoadMapper;
import com.jennifer.meritperk.domain.dao.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServiceImplTest {

    @Mock
    private StudentDao studentDao;
    @Mock
    private SchoolDao schoolDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TeacherDao teacherDao;
    @Mock
    private CloudinaryService cloudinaryService;
    @Mock
    private PayLoadMapper payLoadMapper;
    @Mock
    private AppUserDao appUserDao;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private EmailService emailService;
    @Mock
    private WalletDao walletDao;

    @InjectMocks
    private RegisterServiceImpl registerService;
    @Test
    @DisplayName("Should throw an exception when the email is already taken")
    void registerTeacherWhenEmailIsAlreadyTakenThenThrowException() {
        TeacherRegistrationRequest teacherRegistrationRequest =
                TeacherRegistrationRequest.builder()
                        .email("fortunenwachukwu@gmail.com")
                        .password("1234")
                        .name("Fortune")
                        .phoneNumber("07034999322")
                        .school("Pearlville School")
                        .position("HEADTECHER")
                        .status("RETIRED")
                        .subjectTaught("Mathematics")
                        .yearsOfTeaching(1)
                        .about("I am the head teacher")
                        .build();

        AppUserEntity appUser =
                AppUserEntity.builder()
                        .email("fortunenwachukwu@gmail.com")
                        .password("1234")
                        .role(Role.TEACHER)
                        .build();

        when(appUserDao.findAppUserEntityByEmail(teacherRegistrationRequest.getEmail()))
                .thenReturn(appUser);

        // when + then
        assertThrows(
                EntityAlreadyExistException.class,
                () -> registerService.registerTeacher(teacherRegistrationRequest, null));

        verify(appUserDao, times(1))
                .findAppUserEntityByEmail(teacherRegistrationRequest.getEmail());
    }

    @Test
    @DisplayName("Should throw an exception when the phone number is already taken")
    void registerTeacherWhenPhoneNumberIsAlreadyTakenThenThrowException() {
        TeacherRegistrationRequest teacherRegistrationRequest =
                TeacherRegistrationRequest.builder()
                        .name("Kehinde")
                        .phoneNumber("07034954321")
                        .school("Pearlville School")
                        .yearsOfTeaching(2)
                        .subjectTaught("English")
                        .position("TEACHER")
                        .status("ACTIVE")
                        .about("I am a teacher")
                        .email("kennydman@gmail.com")
                        .password("1234")
                        .build();

        TeacherEntity teacherEntity =
                TeacherEntity.builder()
                        .name("Kenny")
                        .phoneNumber("07034954321")
                        .school(SchoolEntity.builder().build())
                        .nin("nin.jpg")
                        .yearsOfTeaching(1)
                        .subjectsTaught("Maths")
                        .appUserEntity(AppUserEntity.builder().build())
                        .position(Position.TEACHER)
                        .status(Status.ACTIVE)
                        .about("I am a teacher")
                        .build();

        when(teacherDao.findTeacherEntityByPhoneNumber(anyString()))
                .thenReturn(Optional.of(teacherEntity));

        assertThrows(
                EntityAlreadyExistException.class,
                () -> registerService.registerTeacher(teacherRegistrationRequest, null));

        verify(teacherDao, times(1)).findTeacherEntityByPhoneNumber(anyString());
    }
}