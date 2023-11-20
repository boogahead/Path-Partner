package com.ssafy.pathpartner.attraction.service;

import com.ssafy.pathpartner.attraction.dto.AttractionInfoDto;
import com.ssafy.pathpartner.attraction.exception.AttractionInfoNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface AttractionService {

  AttractionInfoDto searchAttractionInfo(String contentId)
      throws SQLException, AttractionInfoNotFoundException;

  List<AttractionInfoDto> searchAllAttractionInfo(String sidoCode, String sigunguCode,
      String[] contentType) throws SQLException;

}
