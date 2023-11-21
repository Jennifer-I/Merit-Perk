package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.usecase.payload.request.StudentRegistrationRequest;
import com.jennifer.meritperk.usecase.payload.request.TeacherRegistrationRequest;
import com.jennifer.meritperk.usecase.payload.response.RegistrationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RegisterService {
    RegistrationResponse registerStudent(StudentRegistrationRequest studentRegistrationRequest) throws Exception;

    RegistrationResponse registerTeacher(TeacherRegistrationRequest teacherRegistrationRequest,MultipartFile file) throws Exception;

    Object verifyUser(String userToken);
}
