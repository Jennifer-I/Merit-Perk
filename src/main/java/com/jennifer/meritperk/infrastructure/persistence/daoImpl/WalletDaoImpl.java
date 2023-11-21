package com.jennifer.meritperk.infrastructure.persistence.daoImpl;

import com.jennifer.meritperk.domain.dao.WalletDao;
import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.transact.WalletEntity;
import com.jennifer.meritperk.infrastructure.persistence.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletDaoImpl extends CrudDaoImpl<WalletEntity, Long> implements WalletDao {
    private final WalletRepository walletRepository;

    protected WalletDaoImpl(WalletRepository walletRepository) {
        super(walletRepository);
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletEntity findWalletEntityByStudent(StudentEntity student) {
        return walletRepository.findWalletEntityByStudent(student);
    }

    @Override
    public Optional<WalletEntity> findWalletEntityByTeacher(TeacherEntity teacher) {
        return walletRepository.findWalletEntityByTeacher(teacher);
    }
}
