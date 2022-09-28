package com.decagon.reward_your_teacher.infrastructure.persistence.daoImpl;

import com.decagon.reward_your_teacher.domain.dao.TeacherDao;
import com.decagon.reward_your_teacher.domain.dao.TokenDao;
import com.decagon.reward_your_teacher.domain.entities.Email.ConfirmationTokenEntity;
import com.decagon.reward_your_teacher.infrastructure.persistence.repository.ConfirmationTokenRepository;
import com.decagon.reward_your_teacher.infrastructure.persistence.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenDaoImpl extends CrudDaoImpl<ConfirmationTokenEntity, Long> implements TokenDao {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    protected TokenDaoImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        super(confirmationTokenRepository);
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public int updateConfirmedAt(String token, LocalDateTime now) {
        return confirmationTokenRepository.updateConfirmedAt(token,now);
    }

    @Override
    public ConfirmationTokenEntity findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
}
