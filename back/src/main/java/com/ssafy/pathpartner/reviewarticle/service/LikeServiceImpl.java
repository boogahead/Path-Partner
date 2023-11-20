package com.ssafy.pathpartner.reviewarticle.service;

import com.ssafy.pathpartner.reviewarticle.dto.LikeDto;
import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.pathpartner.reviewarticle.repository.LikeDao;

import java.util.HashMap;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {

  private final LikeDao likeDao;

  @Autowired
  public LikeServiceImpl(LikeDao likeDao) {
    super();
    this.likeDao = likeDao;
  }

  @Transactional
  @Override
  public boolean like(LikeDto likeDto) throws SQLException {
    return likeDao.like(likeDto) > 0;
  }

  @Transactional
  @Override
  public boolean unlike(LikeDto likeDto) throws SQLException {
    return likeDao.unlike(likeDto) > 0;
  }

  @Transactional
  @Override
  public int countLike(String reviewArticleId) throws SQLException {
    return likeDao.countLike(reviewArticleId);
  }

  @Override
  public List<String> searchAllLikedUsers(String reviewArticleId) throws SQLException {
    return likeDao.selectAllLikeUser(reviewArticleId);
  }

  @Override
  public List<ReviewArticleDto> searchAllMyLikedReviewArticle(String uuid) throws SQLException {
    return likeDao.selectAllMyLikeReviewArticle(uuid);
  }
}
