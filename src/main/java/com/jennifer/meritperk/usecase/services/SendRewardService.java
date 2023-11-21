package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.domain.entities.StudentEntity;
import com.jennifer.meritperk.usecase.payload.request.SendRewardRequest;
import com.jennifer.meritperk.usecase.payload.response.SendRewardResponse;

import java.math.BigDecimal;

public interface SendRewardService {

    SendRewardResponse sendRewardResponse(SendRewardRequest sendRewardRequest) throws Exception;

    BigDecimal getStudentWalletBalance(StudentEntity studentEntity);

}
