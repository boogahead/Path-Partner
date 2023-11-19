package com.ssafy.pathpartner.reviewarticle.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.pathpartner.reviewarticle.repository.LikeDao;

import java.util.HashMap;
import java.util.Map;

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
        String uuid="test";//JWT 로 받을것
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("uuid",uuid);
        params.put("reviewArticleId",likeDto);
        likeMapper.like(params);
    }
    @Transactional
    public void unlike(String likeDto) throws Exception {
        String uuid="test";//JWT 로 받을것
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("uuid",uuid);
        params.put("reviewArticleId",likeDto);
        likeMapper.unlike(params);
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
