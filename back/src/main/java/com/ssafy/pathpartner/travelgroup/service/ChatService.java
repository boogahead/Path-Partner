package com.ssafy.pathpartner.travelgroup.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.pathpartner.travelgroup.exception.TravelGroupNotFoundException;
import java.sql.SQLException;
import java.util.List;
import com.ssafy.pathpartner.travelgroup.dto.ChatDto;

public interface ChatService {

    List<ChatDto>selectChatList(String group_id) throws SQLException;
    void insertChat(ChatDto chatDto) throws SQLException, JsonProcessingException;
}
