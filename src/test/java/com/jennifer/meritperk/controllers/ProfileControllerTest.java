package com.jennifer.meritperk.controllers;

import com.jennifer.meritperk.SecurityConfigTest.TestSecurityConfig;
import com.jennifer.meritperk.domain.entities.enums.Position;
import com.jennifer.meritperk.usecase.payload.request.StudentProfileRequest;
import com.jennifer.meritperk.usecase.payload.request.TeacherProfileRequest;
import com.jennifer.meritperk.usecase.payload.response.EditProfileResponse;
import com.jennifer.meritperk.usecase.payload.response.ViewTeacherProfileResponse;
import com.jennifer.meritperk.usecase.services.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
class ProfileControllerTest {
    static StudentProfileRequest studentProfileRequest;
    static TeacherProfileRequest teacherProfileRequest;

    @MockBean
    private ProfileService profileService;
    @Autowired
    private MockMvc mockMvc;

    static MockMultipartFile firstFile;

    @BeforeEach
    void setUp() throws IOException {
        ViewTeacherProfileResponse viewTeacherProfileResponse = ViewTeacherProfileResponse
                .builder().profilePicture("http://www.image.com").email("k@gmail.com").about("this is about")
                .phoneNumber("081011359665").position(Position.TEACHER.name()).schoolName("Grammer school").name("kenny")
                .build();

        Mockito.when(profileService.viewTeacherByName("kenny")).thenThrow(new RuntimeException("Teacher not found"));
        firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        EditProfileResponse editProfileResponse = EditProfileResponse.builder()
                .name("kenny").email("kendyzman@gmail.com").id(1).build();
         studentProfileRequest = StudentProfileRequest.builder()
                .phone("08101135965").name("PodA").nameOfSchool("Grammer school").email("k@gmail.com").build();
        teacherProfileRequest = TeacherProfileRequest.builder()
                        .nin("1234542").password("1234").schoolTaught("Grammer school").subjectsTaught("english").yearsOfTeaching(3).build();
        Mockito.when(profileService.editStudentProfile(studentProfileRequest)).thenReturn(editProfileResponse);
        Mockito.when(profileService.editTeacherProfile(teacherProfileRequest,firstFile)).thenReturn(editProfileResponse);

    }

    @Test
    void editStudentProfile() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/profile/edit/student")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\":\"kenny\",\n" +
                        "    \"phone\":\"08101135965\",\n" +
                        "    \"email\":\"kenny@gmail\",\n" +
                        "    \"nameOfSchool\":\"Pearlville School\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void editTeacherProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/profile/edit/teacher")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\":\"PodA\",\n" +
                        "    \"email\":\"k@gmail.com\",\n" +
                        "    \"password\":\"1234\",\n" +
                        "    \"schoolTaught\":\"Grammer School\",\n" +
                        "    \"yearsOfTeaching\":\"3\",\n" +
                        "    \"subjectsTaught\":\"english\",\n" +
                        "    \"nin\":\"1234532\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}