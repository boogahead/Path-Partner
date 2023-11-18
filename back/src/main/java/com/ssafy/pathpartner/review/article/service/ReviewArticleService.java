package com.ssafy.pathpartner.review.article.service;

import com.ssafy.pathpartner.review.article.dto.ReviewArticleDto;
import java.util.List;

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
