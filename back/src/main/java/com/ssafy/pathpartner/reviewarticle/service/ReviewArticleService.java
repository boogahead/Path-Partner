package com.ssafy.pathpartner.reviewarticle.service;

import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;

import com.ssafy.pathpartner.reviewarticle.exception.ReviewArticleNotFoundException;
import com.ssafy.pathpartner.reviewarticle.exception.UnauthrizedReviewArticleRequestException;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;

public interface ReviewArticleService {

  boolean createReviewArticle(ReviewArticleDto reviewarticledto) throws SQLException;

  List<ReviewArticleDto> searchAllReviewArticle() throws SQLException;

  ReviewArticleDto searchReviewArticle(String reviewArticleId)
      throws SQLException, ReviewArticleNotFoundException;

  boolean updateReviewArticle(ReviewArticleDto reviewArticledto) throws SQLException;

  boolean deleteReviewArticle(String reviewArticleId, UserDto userDto)
      throws SQLException, ReviewArticleNotFoundException, UnauthrizedReviewArticleRequestException;
}
