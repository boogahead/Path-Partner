package com.ssafy.enjoytrip.notice_article.service;
import java.util.Map;
import com.ssafy.enjoytrip.notice_article.dto.notice_articleDto;
import org.springframework.stereotype.Service;


public interface notice_articleService {
    void createnotice_article(notice_articleDto notice_articleDto) throws Exception;
    void editnotice_article(notice_articleDto notice_articleDto) throws Exception;
    void deletenotice_article(String noticearticle_id) throws Exception;
    notice_articleDto getnotice_article(String noticearticle_id) throws Exception;
    Map<String, Object> getnotice_articleList() throws Exception;

}
