package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.usecase.payload.request.StudentProfileRequest;
import com.jennifer.meritperk.usecase.payload.request.TeacherProfileRequest;
import com.jennifer.meritperk.usecase.payload.response.EditProfileResponse;
import com.jennifer.meritperk.usecase.payload.response.ViewTeacherProfileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {
   EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest);
   EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException;
   List<ViewTeacherProfileResponse> viewTeacherByName(String name);
}
