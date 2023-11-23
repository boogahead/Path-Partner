package com.ssafy.pathpartner.travelgroup.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.travelgroup.dto.ChatDto;
public interface ChatDao {
    List<ChatDto> selectChatList(String group_id) throws SQLException;
    void insertChat(ChatDto chatDto) throws SQLException;
}
