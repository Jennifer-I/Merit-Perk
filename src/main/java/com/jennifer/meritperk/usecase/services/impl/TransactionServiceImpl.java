package com.jennifer.meritperk.usecase.services.impl;

import com.jennifer.meritperk.domain.dao.AppUserDao;
import com.jennifer.meritperk.domain.dao.StudentDao;
import com.jennifer.meritperk.domain.dao.TeacherDao;
import com.jennifer.meritperk.domain.dao.TransactionDao;
import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.enums.Role;
import com.jennifer.meritperk.domain.entities.transact.TransactionEntity;
import com.jennifer.meritperk.infrastructure.configuration.security.UserDetails;
import com.jennifer.meritperk.infrastructure.error_handler.CustomNotFoundException;
import com.jennifer.meritperk.usecase.payload.response.TransactionResponse;
import com.jennifer.meritperk.usecase.services.TransactionService;
import com.jennifer.meritperk.utils.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final StudentDao studentDao;

    private final TeacherDao teacherDao;
    private final AppUserDao userDao;


    @Override
    public List<TransactionResponse> getStudentTransactions(int offset,int pageSize) {
        String email = UserDetails.getLoggedInUserDetails();

        AppUserEntity appUserEntity = userDao.findAppUserEntityByEmailAndRole(email, Role.STUDENT);
        if(appUserEntity == null){
           throw new  CustomNotFoundException("User not found");
        }

        StudentEntity student = studentDao.getStudentEntityByAppUserEntity(appUserEntity);

        Pageable pageable = PageRequest.of(offset,pageSize);
        Page<TransactionEntity> pageList = transactionDao.findTransactionEntitiesByStudentOrderByCreatedAtDesc(pageable,student);
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        pageList.forEach(page->{
            TransactionResponse transactionResponse1 = TransactionResponse.builder()
                    .transactionType(page.getTransactionType())
                    .amount(page.getAmount())
                    .description(page.getDescription())
                    .createdAt(LocalDateTimeConverter.localDateTimeConverter(page.getCreatedAt()))
                    .build();
            transactionResponses.add(transactionResponse1);

        });
        return transactionResponses;
    }


    @Override
    public List<TransactionResponse> getTeacherTransactions(int offset, int pageSize) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof org.springframework.security.core.userdetails.UserDetails)) {
            throw new CustomNotFoundException("user not found");
        }
        String email = ((org.springframework.security.core.userdetails.UserDetails)principal).getUsername();
        AppUserEntity appUserEntity = userDao.findAppUserEntityByEmailAndRole(email,Role.TEACHER);
        if(appUserEntity == null){
          throw new CustomNotFoundException("User not found");
        }

        TeacherEntity teacher = teacherDao.getTeacherEntityByAppUserEntity(appUserEntity);
        if(teacher == null){
            throw new CustomNotFoundException("Invalid user");
        }

        Pageable pageable = PageRequest.of(offset,pageSize);
        Page<TransactionEntity> pageList = transactionDao.findTransactionEntitiesByTeacherOrderByCreatedAtDesc(pageable,teacher);
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        pageList.forEach(page->{
            TransactionResponse transactionResponse1 = TransactionResponse.builder()
                    .transactionType(page.getTransactionType())
                    .amount(page.getAmount())
                    .description(page.getDescription())
                    .createdAt(LocalDateTimeConverter.localDateTimeConverter(page.getCreatedAt()))
                    .studentId(page.getStudent().getId())
                    .studentName(page.getStudent().getName())
                    .studentEmail(page.getStudent().getAppUserEntity().getEmail())
                    .studentSchool(page.getStudent().getSchool().getSchoolName())
                    .studentPhone(page.getStudent().getPhoneNumber()).transactionId(page.getId()).build();
            transactionResponses.add(transactionResponse1);

        });
        return transactionResponses;
    }
}



