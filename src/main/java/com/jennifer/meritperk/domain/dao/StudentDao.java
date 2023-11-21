package com.jennifer.meritperk.domain.dao;


import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.StudentEntity;

public interface StudentDao extends CrudDao<StudentEntity, Long> {
    StudentEntity getStudentEntityByAppUserEntity(AppUserEntity appUserEntity);

}
