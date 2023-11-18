package com.ssafy.pathpartner.reviewarticle.service;

public interface LikeService {
    void like(String likeDto) throws Exception;
    void unlike(String likeDto) throws Exception;
    void countLike(String reviewArticleId) throws Exception;
    void likedUsers(String reviewArticleId) throws Exception;
}
