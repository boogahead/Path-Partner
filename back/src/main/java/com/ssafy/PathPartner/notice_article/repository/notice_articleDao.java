package com.ssafy.PathPartner.notice_article.repository;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.PathPartner.notice_article.dto.notice_articleDto;
@Mapper
public interface notice_articleDao {
    List<notice_articleDto> getnotice_articleList() throws SQLException;
    notice_articleDto getnotice_article(String noticearticle_id) throws SQLException;
    void createnotice_article(notice_articleDto notice_articleDto) throws SQLException;
    void editnotice_article(notice_articleDto notice_articleDto) throws SQLException;
    void deletenotice_article(String noticearticle_id) throws SQLException;
}
