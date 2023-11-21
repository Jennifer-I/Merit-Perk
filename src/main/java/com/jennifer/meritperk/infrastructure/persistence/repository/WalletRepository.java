package com.jennifer.meritperk.infrastructure.persistence.repository;

import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.transact.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findWalletEntityByTeacher(TeacherEntity teacher);

    WalletEntity findWalletEntityByStudent(StudentEntity student);
}
