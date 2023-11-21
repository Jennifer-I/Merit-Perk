package com.jennifer.meritperk.domain.dao;

import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.transact.WalletEntity;

import java.util.Optional;

public interface WalletDao extends CrudDao<WalletEntity, Long> {
    WalletEntity findWalletEntityByStudent(StudentEntity student);
    Optional<WalletEntity> findWalletEntityByTeacher(TeacherEntity teacher);
}
