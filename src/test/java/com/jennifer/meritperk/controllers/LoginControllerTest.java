package com.jennifer.meritperk.controllers;

import com.jennifer.meritperk.usecase.payload.request.LoginRequest;
import com.jennifer.meritperk.usecase.payload.request.SocialLoginRequest;
import com.jennifer.meritperk.usecase.payload.response.LoginResponse;
import com.jennifer.meritperk.usecase.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LoginControllerTest {
static LoginRequest loginRequest;
@Autowired
   private MockMvc mockMvc;
@MockBean
private LoginService loginService;
private static SocialLoginRequest socialLoginRequest;

    @BeforeEach
    void setUp() {
         loginRequest = LoginRequest.builder()
                .email("k@gmail.com")
                .password("1245").build();
         socialLoginRequest = SocialLoginRequest
                .builder().firstName("podA").lastName("Thelma").displayPicture("http://www.Picture.com")
                .email("email@gmail.com").password("1234").build();
        LoginResponse loginResponse = new LoginResponse("token");
        Mockito.when(loginService.loginStudent(loginRequest)).thenReturn(loginResponse);
        Mockito.when(loginService.loginTeacher(loginRequest)).thenReturn(loginResponse);
        Mockito.when(loginService.studentSocialLogin(socialLoginRequest)).thenReturn(loginResponse);
        Mockito.when(loginService.teacherSocialLogin(socialLoginRequest)).thenReturn(loginResponse);
    }

    @Test
    void loginStudent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login/student")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"email\" : \"g@gmail\",\n" +
                        "    \"password\" : \"1234\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isAccepted());

    }

    @Test
    void loginTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login/teacher")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"email\" : \"g@gmail\",\n" +
                        "    \"password\" : \"1234\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void studentSocialLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login/social/student")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"firstName\" : \"Onone\",\n" +
                        "    \"lastName\" : \"PA\",\n" +
                        "    \"email\" : \"onon@gmail.com\",\n" +
                        "    \"displayPicture\" : \"https://cloud.google.com/hfgs.jpg\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void teacherSocialLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login/social/teacher")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"firstName\" : \"Onone\",\n" +
                        "    \"lastName\" : \"PA\",\n" +
                        "    \"email\" : \"onon@gmail.com\",\n" +
                        "    \"displayPicture\" : \"https://cloud.google.com/hfgs.jpg\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}