package com.jennifer.meritperk.infrastructure.persistence.daoImpl;

import com.jennifer.meritperk.domain.dao.StudentDao;
import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.infrastructure.persistence.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentDaoImpl extends CrudDaoImpl<StudentEntity, Long> implements StudentDao {
    private final StudentRepository studentRepository;

    protected StudentDaoImpl(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }


    @Override
    public StudentEntity getStudentEntityByAppUserEntity(AppUserEntity appUserEntity) {
        return studentRepository.getStudentEntityByAppUserEntity(appUserEntity);
    }
}
