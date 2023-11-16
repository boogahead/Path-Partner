package com.ssafy.PathPartner.attraction.service;

import com.ssafy.PathPartner.attraction.dto.AttractionInfoDto;
import com.ssafy.PathPartner.attraction.repository.AttractionDao;
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
  public List<AttractionInfoDto> getAttraction(AttractionInfoDto attractionInfoDto)
      throws SQLException {
    return attractionDao.selectAttraction(attractionInfoDto);
  }

  @Override
  public List<AttractionInfoDto> getAttractionList(String sidoCode, String sigunguCode,
      String[] contentType) throws SQLException {
    return attractionDao.selectAllAttraction(sidoCode, sigunguCode, contentType);
  }

  @Override
  public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) throws SQLException {
    return attractionDao.selectByTitle(title, sidoCode);
  }
}
