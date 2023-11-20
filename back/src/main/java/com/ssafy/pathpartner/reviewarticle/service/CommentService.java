package com.ssafy.pathpartner.reviewarticle.service;

import com.ssafy.pathpartner.reviewarticle.exception.CommentNotFoundException;
import com.ssafy.pathpartner.reviewarticle.exception.UnauthorizedCommentRequestException;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.pathpartner.reviewarticle.dto.CommentDto;

public interface CommentService {

  boolean createComment(CommentDto commentDto) throws SQLException;

  boolean deleteComment(String commentId, UserDto userDto) throws SQLException, CommentNotFoundException, UnauthorizedCommentRequestException;

  List<CommentDto> searchAllComment(String reviewArticleId) throws SQLException;

  int getTotalCommentCount(String reviewArticleId) throws SQLException;

  public boolean updateComment(CommentDto commentDto) throws SQLException;
}
