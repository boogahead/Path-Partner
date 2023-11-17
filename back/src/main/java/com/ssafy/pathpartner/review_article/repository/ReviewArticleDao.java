package com.ssafy.pathpartner.review_article.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.pathpartner.board.dto.FileInfoDto;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.review_article.dto.ReviewArticleDto;
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
