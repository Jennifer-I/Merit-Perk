package com.jennifer.meritperk.infrastructure.persistence.daoImpl;

import com.jennifer.meritperk.domain.dao.TransactionDao;
import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.domain.entities.TeacherEntity;
import com.jennifer.meritperk.domain.entities.transact.TransactionEntity;
import com.jennifer.meritperk.infrastructure.persistence.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionDaoImpl extends CrudDaoImpl<TransactionEntity, Long> implements TransactionDao {
    private final TransactionRepository transactionRepository;

    protected TransactionDaoImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<TransactionEntity> findTransactionEntitiesByStudentOrderByCreatedAtDesc(Pageable pageable, StudentEntity student) {
        return transactionRepository.findTransactionEntitiesByStudentOrderByCreatedAtDesc(pageable,student);
    }

    @Override
    public Page<TransactionEntity> findTransactionEntitiesByTeacherOrderByCreatedAtDesc(Pageable pageable, TeacherEntity teacher) {
        return transactionRepository.findTransactionEntitiesByTeacherOrderByCreatedAtDesc(pageable,teacher);
    }
}
