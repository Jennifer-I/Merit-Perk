package com.jennifer.meritperk.usecase.services.impl;

import com.jennifer.meritperk.domain.dao.ConfirmationTokenDao;
import com.jennifer.meritperk.domain.entities.email.ConfirmationTokenEntity;
import com.jennifer.meritperk.usecase.services.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenDao confirmationTokenDao;
    @Override
    public void saveConfirmationToken(ConfirmationTokenEntity token) {
        confirmationTokenDao.saveRecord(token);
    }
    @Override
    public ConfirmationTokenEntity getToken(String token) {
        return confirmationTokenDao.findByToken(token);
    }
    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenDao.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}