package com.ssafy.pathpartner.noticearticle.service;

import com.ssafy.pathpartner.noticearticle.dto.NoticeArticleDto;
import java.sql.SQLException;
import java.util.List;

public interface NoticeArticleService {

  boolean createNoticeArticle(NoticeArticleDto noticeArticleDto) throws SQLException;

  boolean updateNoticeArticle(NoticeArticleDto noticeArticleDto) throws SQLException;

  boolean deleteNoticeArticle(String noticeArticleId) throws SQLException;

  NoticeArticleDto searchNoticeArticle(String noticeArticleId) throws SQLException;

  List<NoticeArticleDto> searchAllNoticeArticle() throws SQLException;

}
