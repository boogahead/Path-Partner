package com.ssafy.pathpartner.reviewarticle.repository;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDao {
    void like(String likeDto) throws SQLException;
    void unlike(String likeDto) throws SQLException;
    void countLike(String reviewArticleId) throws SQLException;
    List<String> likedUsers(String reviewArticleId) throws SQLException;
}
