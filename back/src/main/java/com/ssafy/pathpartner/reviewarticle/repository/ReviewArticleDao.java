package com.ssafy.pathpartner.reviewarticle.repository;

import java.sql.SQLException;
import java.util.List;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;

@Mapper
public interface ReviewArticleDao {

  int insertReviewArticle(ReviewArticleDto reviewArticledto) throws SQLException;

  List<ReviewArticleDto> selectAllReviewArticle() throws SQLException;

  Optional<ReviewArticleDto> selectReviewArticle(String reviewArticleId) throws SQLException;

  int updateReviewArticle(ReviewArticleDto reviewArticledto) throws SQLException;

  int deleteReviewArticle(String reviewArticleId) throws SQLException;

//  void registerFile(ReviewArticleDto reviewArticledto) throws Exception;

//  void deleteFile(String articleNo) throws Exception;

}
