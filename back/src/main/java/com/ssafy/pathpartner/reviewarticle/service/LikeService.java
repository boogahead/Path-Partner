package com.ssafy.pathpartner.reviewarticle.service;

import com.ssafy.pathpartner.reviewarticle.dto.LikeDto;
import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import java.sql.SQLException;
import java.util.List;

public interface LikeService {

  boolean like(LikeDto likeDto) throws SQLException;

  boolean unlike(LikeDto likeDto) throws SQLException;

  int countLike(String reviewArticleId) throws SQLException;

  List<String> searchAllLikedUsers(String reviewArticleId) throws SQLException;

  List<ReviewArticleDto> searchAllMyLikedReviewArticle(String uuid) throws SQLException;
}
