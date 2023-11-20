package com.ssafy.pathpartner.area.service;

import com.ssafy.pathpartner.area.dto.GugunDto;
import com.ssafy.pathpartner.area.dto.SidoDto;
import java.sql.SQLException;
import java.util.List;

public interface AreaService {

  List<SidoDto> getSidoCode() throws SQLException;

  List<GugunDto> getGunguCode(int sidoCode) throws SQLException;
}
