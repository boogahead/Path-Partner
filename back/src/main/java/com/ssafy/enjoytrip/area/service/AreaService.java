package com.ssafy.enjoytrip.area.service;

import com.ssafy.enjoytrip.area.dto.GugunDto;
import com.ssafy.enjoytrip.area.dto.SidoDto;
import java.util.List;

public interface AreaService {
  List<SidoDto> getSidoCode();

  List<GugunDto> getGunguCode();
}
