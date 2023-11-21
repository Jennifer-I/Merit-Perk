package com.jennifer.meritperk.domain.dao;

import com.jennifer.meritperk.domain.entities.email.ConfirmationTokenEntity;

import java.time.LocalDateTime;

public interface ConfirmationTokenDao extends CrudDao<ConfirmationTokenEntity, Long>{
    int updateConfirmedAt(String token, LocalDateTime now);
    ConfirmationTokenEntity findByToken (String token);
}
