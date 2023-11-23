package com.ssafy.pathpartner.travelgroup.service;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.ssafy.pathpartner.travelgroup.dto.ChatDto;
import com.ssafy.pathpartner.travelgroup.repository.ChatDao;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService{

    private final ChatDao chatDao;
    private final RedisTemplate<String, String> redisTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatServiceImpl(ChatDao chatDao, RedisTemplate<String, String> redisTemplate, SimpMessagingTemplate messagingTemplate) {
        this.chatDao = chatDao;
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public List<ChatDto> selectChatList(String group_id) throws SQLException {
        return chatDao.selectChatList(group_id);
    }

    @Override
    public boolean insertChat(ChatDto chatDto) throws SQLException, JsonProcessingException {
        chatDao.insertChat(chatDto);

        try {
            String chatJson = new ObjectMapper().writeValueAsString(chatDto);

            redisTemplate.opsForValue().set(String.valueOf(chatDto.getChatId()), chatJson);
            messagingTemplate.convertAndSend("/topic/chat/" + chatDto.getGroupId(), chatJson);
        } catch (JsonProcessingException e) {
            // Handle the exception
            // For example, log the error and rethrow the exception
            log.error("Error processing chat message: ", e);
            throw new RuntimeException("Error processing chat message", e);
        }
        return false;
    }
}
