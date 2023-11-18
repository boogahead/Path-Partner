package com.ssafy.pathpartner.reviewarticle.repository;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.pathpartner.reviewarticle.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CommentDao {
    void writeComment(CommentDto commentDto) throws SQLException;
    void deleteComment(String commentNo) throws SQLException;
    List<CommentDto> listComment(String commentNo) throws SQLException;
    int getTotalCommentCount(String reviewArticleId) throws SQLException;

    CommentDto getComment(String boardNo) throws SQLException;
    void editComment(CommentDto commentdto) throws SQLException;
}
