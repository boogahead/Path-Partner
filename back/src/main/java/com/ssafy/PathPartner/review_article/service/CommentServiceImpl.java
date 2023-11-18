package com.ssafy.pathpartner.review_article.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.pathpartner.review_article.dto.CommentDto;
import com.ssafy.pathpartner.review_article.repository.CommentDao;
@Service
public class CommentServiceImpl {
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
    public void deleteComment(String commentNo) throws Exception {
        commentMapper.deleteComment(commentNo);
    }
    public List<CommentDto> listComment(String boardno) throws Exception {
        //Map<String, Object> result = new HashMap<String, Object>();
        List<CommentDto> list = commentMapper.listComment(boardno);
        //result.put("list", list);
        return list;
    }
    public int getTotalCommentCount(String reviewArticleId) throws Exception {
        return commentMapper.getTotalCommentCount(reviewArticleId);
    }
    @Transactional
    public void editComment(String commentNo,String content) throws Exception {
        CommentDto tmp=commentMapper.getComment(commentNo);
        tmp.setContent(content);
        commentMapper.editComment(tmp);
    }
}
