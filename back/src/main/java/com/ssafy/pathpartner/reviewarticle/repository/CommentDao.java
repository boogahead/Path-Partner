package com.ssafy.pathpartner.reviewarticle.repository;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pathpartner.reviewarticle.dto.CommentDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {

  int insertComment(CommentDto commentDto) throws SQLException;

  int deleteComment(String commentId) throws SQLException;

  int updateComment(CommentDto commentdto) throws SQLException;

  int countTotalCommentCount(String reviewArticleId) throws SQLException;

  Optional<CommentDto> selectComment(String commentId) throws SQLException;

  List<CommentDto> selectAllComment(String reviewArticleId) throws SQLException;
}
