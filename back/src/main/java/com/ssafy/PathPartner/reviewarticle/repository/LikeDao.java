package com.ssafy.pathpartner.reviewarticle.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDao {
    void like(Map<String,Object> params) throws SQLException;
    void unlike(Map<String,Object> params) throws SQLException;
    void countLike(String reviewArticleId) throws SQLException;
    List<String> likedUsers(String reviewArticleId) throws SQLException;
}
