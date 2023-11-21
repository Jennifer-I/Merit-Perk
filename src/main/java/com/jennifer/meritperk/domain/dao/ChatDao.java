package com.jennifer.meritperk.domain.dao;

import com.jennifer.meritperk.domain.entities.message.ChatEntity;

import java.time.LocalDateTime;

public interface ChatDao extends CrudDao<ChatEntity, Long> {

    ChatEntity findChatByCreatedAt(LocalDateTime localDateTime);
}
