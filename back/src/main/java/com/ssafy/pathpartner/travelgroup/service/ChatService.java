package com.ssafy.pathpartner.travelgroup.service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.List;
import com.ssafy.pathpartner.travelgroup.dto.ChatDto;

public interface ChatService {

    List<ChatDto>selectChatList(String group_id) throws SQLException;
    boolean insertChat(ChatDto chatDto) throws SQLException, JsonProcessingException;
}
