package com.ssafy.pathpartner.attraction.service;

import com.ssafy.pathpartner.attraction.dto.AttractionInfoDto;
import com.ssafy.pathpartner.attraction.exception.AttractionInfoNotFoundException;
import com.ssafy.pathpartner.attraction.repository.AttractionDao;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl implements AttractionService {

  private final AttractionDao attractionDao;

  @Autowired
  public AttractionServiceImpl(AttractionDao attractionDao) {
    this.attractionDao = attractionDao;
  }

  @Override
  public AttractionInfoDto searchAttractionInfo(String contentId)
      throws SQLException, AttractionInfoNotFoundException {
    return attractionDao.selectAttraction(contentId);
  }

  @Override
  public List<AttractionInfoDto> searchAllAttractionInfo(String sidoCode, String sigunguCode,
      String[] contentType) throws SQLException {
    return attractionDao.selectAllAttraction(sidoCode, sigunguCode, contentType);
  }

}
