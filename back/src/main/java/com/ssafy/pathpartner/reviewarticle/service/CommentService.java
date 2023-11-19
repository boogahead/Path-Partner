package com.ssafy.pathpartner.reviewarticle.service;

import java.util.List;

import com.ssafy.pathpartner.reviewarticle.dto.CommentDto;
public interface CommentService {
    void writeComment(CommentDto commentDto) throws Exception;
    void deleteComment(String commentNo) throws Exception;
    List<CommentDto> listComment(String commentNo) throws Exception;
    int getTotalCommentCount(String reviewArticleId) throws Exception;

    public void editComment(String commentNo,String content) throws Exception;
}
