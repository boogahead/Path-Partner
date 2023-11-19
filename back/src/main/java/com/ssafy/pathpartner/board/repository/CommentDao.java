package com.ssafy.pathpartner.board.repository;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pathpartner.board.dto.CommentDto;
@Mapper
public interface CommentDao {
    void writeComment(CommentDto commentDto) throws SQLException;
    void deleteComment(int commentNo) throws SQLException;
    List<CommentDto> listComment(int boardNo) throws SQLException;
    int getTotalCommentCount(String param) throws SQLException;

}
