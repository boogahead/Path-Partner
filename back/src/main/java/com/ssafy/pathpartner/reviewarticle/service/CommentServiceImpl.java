package com.ssafy.pathpartner.reviewarticle.service;

import com.ssafy.pathpartner.reviewarticle.exception.CommentNotFoundException;
import com.ssafy.pathpartner.reviewarticle.exception.UnauthorizedCommentRequestException;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.pathpartner.reviewarticle.dto.CommentDto;
import com.ssafy.pathpartner.reviewarticle.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;

  @Autowired
  public CommentServiceImpl(CommentDao commentDao) {
    super();
    this.commentDao = commentDao;
  }

  @Override
  public boolean createComment(CommentDto commentDto) throws SQLException {
    return commentDao.insertComment(commentDto) > 0;
  }

  @Override
  public boolean deleteComment(String commentId, UserDto userDto)
      throws SQLException, CommentNotFoundException, UnauthorizedCommentRequestException {
    CommentDto commentDto = commentDao.selectComment(commentId)
        .orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다."));

    if (commentDto.getUuid().equals(userDto.getUuid()) || userDto.getUserType() == 0) {
      return commentDao.deleteComment(commentId) > 0;
    } else {
      throw new UnauthorizedCommentRequestException("댓글을 삭제할 권한이 없습니다.");
    }
  }

  @Override
  public List<CommentDto> searchAllComment(String reviewArticleId) throws SQLException {
    return commentDao.selectAllComment(reviewArticleId);
  }

  @Override
  public int getTotalCommentCount(String reviewArticleId) throws SQLException {
    return commentDao.countTotalCommentCount(reviewArticleId);
  }

  @Override
  public boolean updateComment(CommentDto commentDto) throws SQLException {
    return commentDao.updateComment(commentDto) > 0;
  }

}
