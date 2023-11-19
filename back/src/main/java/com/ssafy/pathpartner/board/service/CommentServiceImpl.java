package com.ssafy.pathpartner.board.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pathpartner.board.dto.CommentDto;
import com.ssafy.pathpartner.board.repository.CommentDao;

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

    @Override
    public int getTotalCommentCount(String reviewArticleId) throws Exception {
        return commentMapper.getTotalCommentCount(reviewArticleId);
    }


}
