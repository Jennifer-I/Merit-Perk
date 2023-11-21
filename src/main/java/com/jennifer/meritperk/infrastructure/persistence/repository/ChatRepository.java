package com.jennifer.meritperk.infrastructure.persistence.repository;

import com.jennifer.meritperk.domain.entities.message.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    Optional<ChatEntity> findChatEntitiesByCreatedAt(LocalDateTime localDateTime);
}
