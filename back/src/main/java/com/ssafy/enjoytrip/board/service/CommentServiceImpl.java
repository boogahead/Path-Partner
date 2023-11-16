package com.ssafy.enjoytrip.board.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.board.dto.CommentDto;
import com.ssafy.enjoytrip.board.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentMapper;

    @Autowired
    public CommentServiceImpl(CommentDao commentMapper) {
        super();
        this.commentMapper = commentMapper;
    }

    @Transactional
    public void writeComment(CommentDto commentDto) throws Exception {
        commentMapper.writeComment(commentDto);
    }

    @Transactional
    public void deleteComment(int commentNo) throws Exception {
        commentMapper.deleteComment(commentNo);
    }

    public List<CommentDto> listComment(int boardno) throws Exception {
        //Map<String, Object> result = new HashMap<String, Object>();
        List<CommentDto> list = commentMapper.listComment(boardno);
        //result.put("list", list);
        return list;
    }

    public int getTotalCommentCount(Map<String, Object> param) throws Exception {
        return commentMapper.getTotalCommentCount(param);
    }

}