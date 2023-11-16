package com.ssafy.pathpartner.notice_article.service;
import java.util.Map;
import com.ssafy.pathpartner.notice_article.dto.notice_articleDto;


public interface notice_articleService {
    void createnotice_article(notice_articleDto notice_articleDto) throws Exception;
    void editnotice_article(notice_articleDto notice_articleDto) throws Exception;
    void deletenotice_article(String noticearticle_id) throws Exception;
    notice_articleDto getnotice_article(String noticearticle_id) throws Exception;
    Map<String, Object> getnotice_articleList() throws Exception;

}
