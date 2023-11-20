package com.ssafy.pathpartner.reviewarticle.service;

import com.ssafy.pathpartner.reviewarticle.exception.ReviewArticleNotFoundException;
import com.ssafy.pathpartner.reviewarticle.exception.UnauthrizedReviewArticleRequestException;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import com.ssafy.pathpartner.reviewarticle.repository.ReviewArticleDao;

@Service
public class ReviewArticleServiceImpl implements ReviewArticleService {

  private final ReviewArticleDao reviewArticleDao;

  @Autowired
  public ReviewArticleServiceImpl(ReviewArticleDao reviewArticleDao) {
    super();
    this.reviewArticleDao = reviewArticleDao;
  }

  @Override
  @Transactional
  public boolean createReviewArticle(ReviewArticleDto reviewArticledto) throws SQLException {
    return reviewArticleDao.insertReviewArticle(reviewArticledto) > 0;
  }

  @Override
  public List<ReviewArticleDto> searchAllReviewArticle() throws SQLException {
    return reviewArticleDao.selectAllReviewArticle();
  }

  @Override
  public ReviewArticleDto searchReviewArticle(String reviewArticleId) throws SQLException {
    return reviewArticleDao.selectReviewArticle(reviewArticleId)
        .orElseThrow(() -> new ReviewArticleNotFoundException("후기글을 찾을 수 없습니다."));
  }

  @Override
  @Transactional
  public boolean updateReviewArticle(ReviewArticleDto reviewArticledto) throws SQLException {
    return reviewArticleDao.updateReviewArticle(reviewArticledto) > 0;
            /*List<ReviewArticleDto> reviewArticleDtos = reviewArticledto.getReviewArticleDtos();
            if (reviewArticleDtos != null && !reviewArticleDtos.isEmpty()) {
                reviewArticleMapper.registerFile(reviewArticledto);
            }*/
  }

  @Override
  @Transactional
  public boolean deleteReviewArticle(String reviewArticleId, UserDto userDto) throws SQLException {
    String writerUuid = reviewArticleDao.selectReviewArticle(reviewArticleId)
        .orElseThrow(() -> new ReviewArticleNotFoundException("후기글을 찾을 수 없습니다.")).getWriterUuid();

    // 권한 체크
    if (writerUuid.equals(userDto.getUuid()) || userDto.getUserType() == 0) {
      return reviewArticleDao.deleteReviewArticle(reviewArticleId) > 0;
    } else {
      throw new UnauthrizedReviewArticleRequestException("후기글에 삭제 권한이 없습니다.");
    }
  }
}
