package com.jennifer.meritperk.usecase.services.impl;

import com.jennifer.meritperk.domain.dao.AppUserDao;
import com.jennifer.meritperk.domain.dao.SchoolDao;
import com.jennifer.meritperk.domain.dao.StudentDao;
import com.jennifer.meritperk.domain.dao.TeacherDao;
import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.SchoolEntity;
import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.enums.Position;
import com.jennifer.meritperk.domain.entities.enums.Role;
import com.jennifer.meritperk.domain.entities.enums.Status;
import com.jennifer.meritperk.infrastructure.configuration.security.UserDetails;
import com.jennifer.meritperk.infrastructure.error_handler.CustomNotFoundException;
import com.jennifer.meritperk.infrastructure.error_handler.EntityAlreadyExistException;
import com.jennifer.meritperk.usecase.payload.request.StudentProfileRequest;
import com.jennifer.meritperk.usecase.payload.request.TeacherProfileRequest;
import com.jennifer.meritperk.usecase.payload.response.EditProfileResponse;
import com.jennifer.meritperk.usecase.payload.response.ViewTeacherProfileResponse;
import com.jennifer.meritperk.usecase.services.ProfileService;
import com.jennifer.meritperk.utils.CloudinaryService;
import com.jennifer.meritperk.utils.PayLoadMapper;
import com.jennifer.meritperk.utils.RequestMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final StudentDao studentDao;
    private final SchoolDao schoolDao;
    private final AppUserDao appUserDao;
    private final TeacherDao teacherDao;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    private final RequestMapper requestMapper;
    private final PayLoadMapper payLoadMapper;

    @Override
    public EditProfileResponse editStudentProfile(StudentProfileRequest studentProfileRequest) {
        String email = UserDetails.getLoggedInUserDetails();

        SchoolEntity school = schoolDao.findSchool(studentProfileRequest.getNameOfSchool())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));

        AppUserEntity student = appUserDao.findAppUserEntityByEmailAndRole(email,Role.STUDENT);
        if(student == null){
           throw new CustomNotFoundException("student not found");
        }
        AppUserEntity appUserEntity1 = appUserDao.findAppUserEntityByEmail(studentProfileRequest.getEmail());
        if(appUserEntity1 != null){
            throw new EntityAlreadyExistException("Email already exist");
        }
        student.setEmail(studentProfileRequest.getEmail());
        AppUserEntity appUserEntity = appUserDao.saveRecord(student);

        StudentEntity studentEntity = studentDao.getStudentEntityByAppUserEntity(appUserEntity);
        studentEntity.setSchool(school);
        studentEntity.setName(studentProfileRequest.getName());
        studentEntity.setPhoneNumber(studentProfileRequest.getPhone());
        EditProfileResponse editProfileResponse = payLoadMapper.studentEditMapper(studentDao.saveRecord(studentEntity));

        return editProfileResponse;
    }

    @Override
    public EditProfileResponse editTeacherProfile(TeacherProfileRequest teacherProfileRequest, MultipartFile file) throws IOException {
        String email = UserDetails.getLoggedInUserDetails();


        SchoolEntity school = schoolDao.findSchool(teacherProfileRequest.getSchool())
                .orElseThrow(() -> new CustomNotFoundException("School not found"));

        AppUserEntity appUserEntity = appUserDao.findAppUserEntityByEmailAndRole(email,Role.TEACHER);
        if(appUserEntity == null){
            throw new CustomNotFoundException("Teacher not found");
        }
        AppUserEntity appUserEntity1 = appUserDao.findAppUserEntityByEmail(teacherProfileRequest.getEmail());
        if(appUserEntity1 != null && !(email.equals(teacherProfileRequest.getEmail()))){
            throw new EntityAlreadyExistException("Email already exist");
        }
        appUserEntity.setEmail(teacherProfileRequest.getEmail());
        appUserEntity.setPassword(passwordEncoder.encode(teacherProfileRequest.getPassword()));

        String url = cloudinaryService.uploadImage(file);
        TeacherEntity teacher = teacherDao.getTeacherEntityByAppUserEntity(appUserEntity);
        teacher.setName(teacherProfileRequest.getName());
        teacher.setSchool(school);
        teacher.setYearsOfTeaching(teacherProfileRequest.getYearsOfTeaching());
        teacher.setPhoneNumber(teacherProfileRequest.getPhoneNumber());
        teacher.setNin(url);
        teacher.setSubjectsTaught(teacherProfileRequest.getSubjectTaught());
        teacher.setAbout(teacherProfileRequest.getAbout());
        teacher.setPosition(Position.valueOf(teacherProfileRequest.getPosition().toUpperCase()));
        teacher.setStatus(Status.valueOf(teacherProfileRequest.getStatus().toUpperCase()));

        EditProfileResponse editProfileResponse = payLoadMapper.TeacherEditMapper( teacherDao.saveRecord(teacher));
        return editProfileResponse;
    }

    @Override
    public List<ViewTeacherProfileResponse> viewTeacherByName(String name) {
        List<TeacherEntity> teachers = teacherDao.findTeacherEntitiesByName(name).orElseThrow(()-> new CustomNotFoundException("Teacher not found"));
        List<ViewTeacherProfileResponse> viewTeacherProfileResponses = new ArrayList<>();
        teachers.forEach(teacher -> {
            ViewTeacherProfileResponse viewTeacherProfileResponse = requestMapper
                    .viewTeacherProfileResponseToTeacherEntityMapper(teacher);
            viewTeacherProfileResponses.add(viewTeacherProfileResponse);
        });
        return viewTeacherProfileResponses;
    }
}