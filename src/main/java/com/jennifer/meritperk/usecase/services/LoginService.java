package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.usecase.payload.request.LoginRequest;
import com.jennifer.meritperk.usecase.payload.request.SocialLoginRequest;
import com.jennifer.meritperk.usecase.payload.response.LoginResponse;

public interface LoginService {
    LoginResponse loginStudent(LoginRequest loginRequest);

    LoginResponse studentSocialLogin(SocialLoginRequest socialLoginRequest);

    LoginResponse loginTeacher(LoginRequest loginRequest);

    LoginResponse teacherSocialLogin(SocialLoginRequest socialLoginRequest);
}
