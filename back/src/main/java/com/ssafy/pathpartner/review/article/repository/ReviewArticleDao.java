package com.ssafy.pathpartner.review.article.repository;
import com.ssafy.pathpartner.review.article.dto.ReviewArticleDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewArticleDao {
    void writeArticle(ReviewArticleDto reviewArticledto) throws SQLException;

    void registerFile(ReviewArticleDto reviewArticledto) throws Exception;

    List<ReviewArticleDto> listArticle() throws SQLException;

    int getTotalArticleCount(Map<String, Object> param) throws SQLException;

    ReviewArticleDto getArticle(String articleNo) throws SQLException;

    void updateHit(int articleNo) throws SQLException;

    void modifyArticle(ReviewArticleDto reviewArticledto) throws SQLException;

    void deleteFile(String articleNo) throws Exception;

    void deleteArticle(String articleNo) throws SQLException;

}
