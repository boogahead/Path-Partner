package com.ssafy.pathpartner.review_article.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ssafy.pathpartner.review_article.dto.CommentDto;
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
