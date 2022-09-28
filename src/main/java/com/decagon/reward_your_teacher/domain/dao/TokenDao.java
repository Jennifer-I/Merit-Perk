package com.decagon.reward_your_teacher.domain.dao;

import com.decagon.reward_your_teacher.domain.entities.Email.ConfirmationTokenEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;

import java.time.LocalDateTime;

public interface TokenDao extends CrudDao<ConfirmationTokenEntity, Long>{
    int updateConfirmedAt(String token, LocalDateTime now);
    ConfirmationTokenEntity findByToken (String token);
}
