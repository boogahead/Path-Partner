package com.ssafy.PathPartner.notice_article.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.PathPartner.notice_article.dto.notice_articleDto;
import com.ssafy.PathPartner.notice_article.repository.notice_articleDao;
@Service
public class notice_articleServiceImpl implements notice_articleService{
    private notice_articleDao notice_articleMapper;
    @Autowired
    public notice_articleServiceImpl(notice_articleDao notice_articleMapper) {
        super();
        this.notice_articleMapper = notice_articleMapper;
    }
    @Transactional
    public void createnotice_article(notice_articleDto notice_articleDto) throws Exception {
        notice_articleMapper.createnotice_article(notice_articleDto);
    }
    @Transactional
    public void editnotice_article(notice_articleDto notice_articleDto) throws Exception {
        notice_articleMapper.editnotice_article(notice_articleDto);
    }
    @Transactional
    public void deletenotice_article(String noticearticle_id) throws Exception {
        notice_articleMapper.deletenotice_article(noticearticle_id);
    }
    @Transactional
    public notice_articleDto getnotice_article(String noticearticle_id) throws Exception {
        return notice_articleMapper.getnotice_article(noticearticle_id);
    }
    @Transactional
    public Map<String, Object> getnotice_articleList() throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        List<notice_articleDto> list = notice_articleMapper.getnotice_articleList();
        param.put("list", list);
        return param;
    }

}
