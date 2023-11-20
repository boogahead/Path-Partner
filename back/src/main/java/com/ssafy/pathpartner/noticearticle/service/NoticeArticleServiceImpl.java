package com.ssafy.pathpartner.noticearticle.service;

import com.ssafy.pathpartner.noticearticle.dto.NoticeArticleDto;
import com.ssafy.pathpartner.noticearticle.exception.NoticeArticleNotFound;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pathpartner.noticearticle.repository.NoticeArticleDao;

@Service
public class NoticeArticleServiceImpl implements NoticeArticleService {

  private final NoticeArticleDao noticeArticleDao;

  @Autowired
  public NoticeArticleServiceImpl(NoticeArticleDao noticeArticleDao) {
    super();
    this.noticeArticleDao = noticeArticleDao;
  }

  @Override
  public boolean createNoticeArticle(NoticeArticleDto noticeArticleDto) throws SQLException {
    return noticeArticleDao.insertNoticeArticle(noticeArticleDto) > 0;
  }

  @Override
  public boolean updateNoticeArticle(NoticeArticleDto noticeArticleDto) throws SQLException {
    return noticeArticleDao.updateNoticeArticle(noticeArticleDto) > 0;
  }

  @Override
  public boolean deleteNoticeArticle(String noticeArticleId) throws SQLException {
    return noticeArticleDao.deleteNoticeArticle(noticeArticleId) > 0;
  }

  @Override
  public NoticeArticleDto searchNoticeArticle(String noticeArticleId) throws SQLException, NoticeArticleNotFound {
    return noticeArticleDao.selectNoticeArticle(noticeArticleId).orElseThrow(
        () -> new NoticeArticleNotFound(
            String.format("[id: %]에 해당하는 글을 찾을 수 없습니다.", noticeArticleId)));
  }

  @Override
  public List<NoticeArticleDto> searchAllNoticeArticle() throws SQLException {
    return noticeArticleDao.selectAllNoticeArticle();
  }
}
