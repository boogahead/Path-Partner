package com.ssafy.PathPartner.board.service;
import java.util.List;
import java.util.Map;
import com.ssafy.PathPartner.board.dto.CommentDto;

public interface CommentService {
    void writeComment(CommentDto commentDto) throws Exception;
    void deleteComment(int commentNo) throws Exception;
    List<CommentDto> listComment(int boardNo) throws Exception;
    int getTotalCommentCount(Map<String, Object> param) throws Exception;

}
