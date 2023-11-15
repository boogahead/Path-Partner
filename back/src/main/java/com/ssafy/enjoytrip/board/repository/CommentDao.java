package com.ssafy.enjoytrip.board.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.dto.CommentDto;
@Mapper
public interface CommentDao {
    void writeComment(CommentDto commentDto) throws SQLException;
    void deleteComment(int commentNo) throws SQLException;
    List<CommentDto> listComment(int boardNo) throws SQLException;
    int getTotalCommentCount(Map<String, Object> param) throws SQLException;

}
