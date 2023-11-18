package com.ssafy.pathpartner.noticearticle.repository;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.noticearticle.dto.notice_articleDto;
@Mapper
public interface NoticeArticleDao {
    List<notice_articleDto> getnotice_articleList() throws SQLException;
    notice_articleDto getnotice_article(String noticearticle_id) throws SQLException;
    void createnotice_article(notice_articleDto notice_articleDto) throws SQLException;
    void editnotice_article(notice_articleDto notice_articleDto) throws SQLException;
    void deletenotice_article(String noticearticle_id) throws SQLException;
}
