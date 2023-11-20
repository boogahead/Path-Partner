package com.ssafy.pathpartner.attraction.repository;

import com.ssafy.pathpartner.attraction.dto.AttractionInfoDto;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttractionDao {

  AttractionInfoDto selectAttraction(String contentId) throws SQLException;

  List<AttractionInfoDto> selectAllAttraction(@Param("sidoCode") String sidoCode,
      @Param("sigunguCode") String sigunguCode,
      @Param("contentType") String[] contentType) throws SQLException;

}
