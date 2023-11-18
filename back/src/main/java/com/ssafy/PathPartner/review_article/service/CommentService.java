package com.ssafy.pathpartner.review_article.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ssafy.pathpartner.review_article.dto.CommentDto;
public interface CommentService {
    void writeComment(CommentDto commentDto) throws Exception;
    void deleteComment(String commentNo) throws Exception;
    List<CommentDto> listComment(String commentNo) throws Exception;
    int getTotalCommentCount(String reviewArticleId) throws Exception;

    public void editComment(String commentNo,String content) throws Exception;
}
