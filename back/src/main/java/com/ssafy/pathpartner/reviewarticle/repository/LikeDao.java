package com.ssafy.pathpartner.reviewarticle.repository;

import com.ssafy.pathpartner.reviewarticle.dto.LikeDto;
import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDao {

  int like(LikeDto likeDto) throws SQLException;

  int unlike(LikeDto likeDto) throws SQLException;

  int countLike(String reviewArticleId) throws SQLException;

  List<String> selectAllLikeUser(String reviewArticleId) throws SQLException;

  List<ReviewArticleDto> selectAllMyLikeReviewArticle(String uuid) throws SQLException;
}
