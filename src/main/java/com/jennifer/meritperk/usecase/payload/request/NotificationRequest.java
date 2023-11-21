package com.jennifer.meritperk.usecase.payload.request;

import com.jennifer.meritperk.domain.entities.enums.NotificationType;

public record NotificationRequest(String notificationBody, NotificationType notificationType) {

}
