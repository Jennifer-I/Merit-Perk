package com.decagon.reward_your_teacher.usecase.services.impl;

import com.decagon.reward_your_teacher.domain.dao.NotificationDao;
import com.decagon.reward_your_teacher.domain.dao.StudentDao;
import com.decagon.reward_your_teacher.domain.dao.TeacherDao;
import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;
import com.decagon.reward_your_teacher.infrastructure.error_handler.CustomNotFoundException;
import com.decagon.reward_your_teacher.usecase.payload.request.TransactionRequest;
import com.decagon.reward_your_teacher.usecase.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationDao notificationDao;
    private StudentDao studentDao;
    private TeacherDao teacherDao;


    @Override
    public NotificationEntity studentSendMoneyNotification(TransactionRequest transactionRequest) {
        Long teacherId = transactionRequest.getTeacherId();
        TeacherEntity teacher = teacherDao.findById(teacherId).orElse(null);
        NotificationEntity notification = new NotificationEntity();

        try {
            if (teacher == null) {
                throw new CustomNotFoundException("Id " + teacherId + " is not valid");
            }

            String message = "Successfully transferred " + transactionRequest.getAmount() + " to " + teacher.getName();
            notification.setCreatedAt(LocalDateTime.now());
            notification.setMessage(message);
            notification.setTeacher(teacher);
            notificationDao.saveRecord(notification);
            return notification;
        } catch (CustomNotFoundException e) {
            System.out.println(e.getMessage());
        }
        notification.setMessage("Transaction failed!!!. Invalid user.");
        notification.setCreatedAt(LocalDateTime.now());
        return notification;
    }

    @Override
    public NotificationEntity walletFundingNotification(TransactionRequest transactionRequest) {
        Long studentId = transactionRequest.getStudentId();
        StudentEntity studentEntity = studentDao.findById(studentId).orElse(null);

        if (studentEntity == null) {
            throw new CustomNotFoundException("Student with Id " + studentId + " is not valid");
        }

        NotificationEntity notification = new NotificationEntity();
        String message = "You have successfully funded your wallet with " + transactionRequest.getAmount();
        notification.setCreatedAt(transactionRequest.getCreatedAt());
        notification.setMessage(message);
        notification.setStudent(studentEntity);
        return notificationDao.saveRecord(notification);
    }


    @Override
    public NotificationEntity teacherReceivedNotification(TransactionRequest transactionRequest) {
        NotificationEntity notification = new NotificationEntity();
        Long teacherId = transactionRequest.getTeacherId();
        TeacherEntity teacher = teacherDao.findById(teacherId).orElse(null);
        if (teacher == null) {
            throw new CustomNotFoundException(" Id " + teacherId + " is not valid");
        }
        String message = "You received " + transactionRequest.getAmount();
        notification.setCreatedAt(LocalDateTime.now());
        notification.setMessage(message);
        notification.setTeacher(teacher);
        return notificationDao.saveRecord(notification);
    }

    @Override
    public List<NotificationEntity> allNotificationsOfA_StudentById(Long studentId) {
        StudentEntity student = studentDao.findById(studentId).orElseThrow(null);
        List<NotificationEntity> notificationEntity = notificationDao.findNotificationEntitiesByStudent(student);
        if (notificationEntity.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notificationEntity;
    }

    @Override
    public List<NotificationEntity> allNotificationsOfA_TeacherById(Long teacherId) {
        TeacherEntity teacher = teacherDao.findById(teacherId).orElseThrow(null);
        List<NotificationEntity> notificationEntity = notificationDao.findNotificationEntitiesByTeacher(teacher);
        if (notificationEntity.isEmpty()) {
            throw new CustomNotFoundException("Notification is empty");
        }
        return notificationEntity;
    }

}
