package com.ssafy.pathpartner.noticearticle.repository;
import com.ssafy.pathpartner.noticearticle.dto.NoticeArticleDto;
import com.ssafy.pathpartner.noticearticle.exception.NoticeArticleNotFound;
import java.sql.SQLException;
import java.util.List;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface NoticeArticleDao {
    List<NoticeArticleDto> selectAllNoticeArticle() throws SQLException;
    Optional<NoticeArticleDto> selectNoticeArticle(String noticeArticleId) throws SQLException, NoticeArticleNotFound;
    int insertNoticeArticle(NoticeArticleDto noticeArticleDto) throws SQLException;
    int updateNoticeArticle(NoticeArticleDto noticeArticleDto) throws SQLException;
    int deleteNoticeArticle(String noticeArticleId ) throws SQLException;
}
