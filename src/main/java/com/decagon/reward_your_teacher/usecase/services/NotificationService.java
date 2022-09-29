package com.decagon.reward_your_teacher.usecase.services;

import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;
import com.decagon.reward_your_teacher.usecase.payload.request.NotificationRequest;
import com.decagon.reward_your_teacher.usecase.payload.request.TransactionRequest;
import com.decagon.reward_your_teacher.usecase.payload.response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationEntity studentSendMoneyNotification(TransactionRequest transactionRequest);

    NotificationEntity walletFundingNotification(TransactionRequest transactionRequest);

    NotificationEntity teacherReceivedNotification(TransactionRequest transactionRequest);

    List<NotificationRequest> allNotificationsOfA_StudentById(Long studentId);

    List<NotificationRequest> allNotificationsOfA_TeacherById(Long teacherId);

    NotificationResponse studentAppreciatedNotification(Long studentId, Long teacherId);

}


