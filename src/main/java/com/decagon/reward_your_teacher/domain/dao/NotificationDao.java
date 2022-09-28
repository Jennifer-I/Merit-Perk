package com.decagon.reward_your_teacher.domain.dao;

import com.decagon.reward_your_teacher.domain.entities.StudentEntity;
import com.decagon.reward_your_teacher.domain.entities.TeacherEntity;
import com.decagon.reward_your_teacher.domain.entities.message.NotificationEntity;

import java.util.List;
import java.util.Optional;


public interface NotificationDao extends CrudDao<NotificationEntity, Long> {
  List<NotificationEntity> findNotificationEntitiesByStudent(StudentEntity student);

    List<NotificationEntity> findNotificationEntitiesByTeacher(TeacherEntity teacher);

}
