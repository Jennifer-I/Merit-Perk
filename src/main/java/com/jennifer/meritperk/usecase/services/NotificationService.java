package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.domain.entities.message.NotificationEntity;
import com.jennifer.meritperk.usecase.payload.request.TransactionRequest;
import com.jennifer.meritperk.usecase.payload.response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationEntity studentSendMoneyNotification(TransactionRequest transactionRequest);

    NotificationEntity walletFundingNotification(TransactionRequest transactionRequest);

    NotificationEntity teacherReceivedNotification(TransactionRequest transactionRequest);

    List<NotificationResponse> allNotificationsOfA_StudentById();

    List<NotificationResponse> allNotificationsOfA_TeacherById();

    NotificationResponse studentAppreciatedNotification(Long transactionId);

}


