package com.ssafy.enjoytrip.area.repository;

import com.ssafy.enjoytrip.area.dto.GugunDto;
import com.ssafy.enjoytrip.area.dto.SidoDto;
import java.util.List;

public interface AreaDao {

  List<SidoDto> getSidoCode();

  List<GugunDto> getGunguCode();
}
