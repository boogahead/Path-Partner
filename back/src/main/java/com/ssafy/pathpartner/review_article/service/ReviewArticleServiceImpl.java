package com.ssafy.pathpartner.review_article.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pathpartner.review_article.dto.ReviewArticleDto;
import com.ssafy.pathpartner.review_article.repository.ReviewArticleDao;

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

            /*if ("user_id".equals(key))
                param.put("key", key == null ? "" : "user_id");
            int totalArticleCount = reviewArticleMapper.getTotalArticleCount(param);
            int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;*/

            /*ReviewArticleDto reviewArticleDto = new ReviewArticleDto();
            reviewArticleDto.setReviewArticleDtos(list);
            reviewArticleDto.setTotalArticleCount(totalArticleCount);
            reviewArticleDto.setCurrentPage(currentPage);
            reviewArticleDto.setTotalPageCount(totalPageCount);
            reviewArticleDto.setStart(start);
            reviewArticleDto.setListSize(sizePerPage);*/
            return list;
        }

        @Override
        public ReviewArticleDto getArticle(String articleNo) throws Exception {
            return reviewArticleMapper.getArticle(articleNo);
        }

        @Override
        public void updateHit(int articleNo) throws Exception {
            reviewArticleMapper.updateHit(articleNo);
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