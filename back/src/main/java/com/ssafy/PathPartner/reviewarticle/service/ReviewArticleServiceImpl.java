package com.ssafy.pathpartner.reviewarticle.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import com.ssafy.pathpartner.reviewarticle.repository.ReviewArticleDao;

@Service
public class ReviewArticleServiceImpl implements ReviewArticleService {

        private ReviewArticleDao reviewArticleMapper;

        @Autowired
        public ReviewArticleServiceImpl(ReviewArticleDao reviewArticleMapper) {
            super();
            this.reviewArticleMapper = reviewArticleMapper;
        }

        @Override
        @Transactional
        public void writeArticle(ReviewArticleDto reviewArticledto) throws Exception {
            reviewArticleMapper.writeArticle(reviewArticledto);
            /*List<ReviewArticleDto> reviewArticleDtos = reviewArticledto.getReviewArticleDtos();
            if (reviewArticleDtos != null && !reviewArticleDtos.isEmpty()) {
                reviewArticleMapper.registerFile(reviewArticledto);
            }*/
        }

        @Override
        public List<ReviewArticleDto> listArticle() throws Exception {
            List<ReviewArticleDto> list = reviewArticleMapper.listArticle();
            return list;
        }

        @Override
        public ReviewArticleDto getArticle(String articleNo) throws Exception {
            return reviewArticleMapper.getArticle(articleNo);
        }

        @Override
        @Transactional
        public void modifyArticle(ReviewArticleDto reviewArticledto) throws Exception {
            reviewArticleMapper.modifyArticle(reviewArticledto);
            /*List<ReviewArticleDto> reviewArticleDtos = reviewArticledto.getReviewArticleDtos();
            if (reviewArticleDtos != null && !reviewArticleDtos.isEmpty()) {
                reviewArticleMapper.registerFile(reviewArticledto);
            }*/
        }

        @Override
        @Transactional
        public void deleteArticle(String articleNo) throws Exception {
            reviewArticleMapper.deleteArticle(articleNo);
        }
}
