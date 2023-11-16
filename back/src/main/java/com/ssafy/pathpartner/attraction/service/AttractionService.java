package com.ssafy.pathpartner.attraction.service;

import com.ssafy.pathpartner.attraction.dto.AttractionInfoDto;
import java.sql.SQLException;
import java.util.List;

public interface AttractionService {

	List<AttractionInfoDto> getAttraction(AttractionInfoDto attractionInfoDto) throws SQLException;
	List<AttractionInfoDto> getAttractionList(String sidoCode, String sigunguCode, String[] contentType) throws SQLException;

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode) throws SQLException;
	
}
