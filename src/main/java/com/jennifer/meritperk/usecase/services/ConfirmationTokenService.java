package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.domain.entities.email.ConfirmationTokenEntity;

public interface ConfirmationTokenService {
     void saveConfirmationToken(ConfirmationTokenEntity token);
     ConfirmationTokenEntity getToken(String token);
     int setConfirmedAt(String token);
}
