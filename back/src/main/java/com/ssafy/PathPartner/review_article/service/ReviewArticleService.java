package com.ssafy.pathpartner.review_article.service;

import com.ssafy.pathpartner.review_article.dto.ReviewArticleDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReviewArticleService {
    void writeArticle(ReviewArticleDto reviewarticledto) throws Exception;
    List<ReviewArticleDto> listArticle() throws Exception;
    //	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
    ReviewArticleDto getArticle(String articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;

    void modifyArticle(ReviewArticleDto reviewarticledto) throws Exception;
    //
    void deleteArticle(String articleNo) throws Exception;
}
