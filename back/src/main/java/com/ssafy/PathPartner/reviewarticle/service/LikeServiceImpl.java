package com.ssafy.pathpartner.reviewarticle.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.pathpartner.reviewarticle.repository.LikeDao;
@Service
public class LikeServiceImpl implements LikeService{
    private LikeDao likeMapper;
    @Autowired
    public LikeServiceImpl(LikeDao likeMapper) {
        super();
        this.likeMapper = likeMapper;
    }
    @Transactional
    public void like(String likeDto) throws Exception {
        likeMapper.like(likeDto);
    }
    @Transactional
    public void unlike(String likeDto) throws Exception {
        likeMapper.unlike(likeDto);
    }
    @Transactional
    public void countLike(String reviewArticleId) throws Exception {
        likeMapper.countLike(reviewArticleId);
    }
    @Transactional
    public void likedUsers(String reviewArticleId) throws Exception {
        likeMapper.likedUsers(reviewArticleId);
    }
}
