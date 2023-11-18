package com.ssafy.pathpartner.noticearticle.service;
import java.util.Map;
import com.ssafy.pathpartner.noticearticle.dto.notice_articleDto;


public interface NoticeArticleService {
    void createnotice_article(notice_articleDto notice_articleDto) throws Exception;
    void editnotice_article(notice_articleDto notice_articleDto) throws Exception;
    void deletenotice_article(String noticearticle_id) throws Exception;
    notice_articleDto getnotice_article(String noticearticle_id) throws Exception;
    Map<String, Object> getnotice_articleList() throws Exception;

}
