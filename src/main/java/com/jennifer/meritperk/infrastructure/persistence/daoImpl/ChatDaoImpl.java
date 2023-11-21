package com.jennifer.meritperk.infrastructure.persistence.daoImpl;

import com.jennifer.meritperk.domain.dao.ChatDao;
import com.jennifer.meritperk.domain.entities.message.ChatEntity;
import com.jennifer.meritperk.infrastructure.persistence.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatDaoImpl extends CrudDaoImpl<ChatEntity, Long> implements ChatDao {

    private final ChatRepository chatRepository;

    public ChatDaoImpl(ChatRepository chatRepository) {
        super(chatRepository);
        this.chatRepository = chatRepository;
    }

    @Override
    public ChatEntity findChatByCreatedAt(LocalDateTime localDateTime) {
        Optional<ChatEntity> optionalChat = chatRepository.findChatEntitiesByCreatedAt(localDateTime);

        if (!optionalChat.isPresent()) {
            throw new RuntimeException();
        }

        return optionalChat.get();
    }


}
