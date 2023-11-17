package com.ssafy.pathpartner.attraction.repository;

import com.ssafy.pathpartner.attraction.dto.AttractionInfoDto;
import java.sql.SQLException;
import java.util.List;

public interface AttractionDao {

  List<AttractionInfoDto> selectAttraction(AttractionInfoDto attractionInfoDto)  throws SQLException;

  List<AttractionInfoDto> selectAllAttraction(String sidoCode, String sigunguCode, String[] contentType)  throws SQLException;

  List<AttractionInfoDto> selectByTitle(String title, int sidoCode)  throws SQLException;

}
