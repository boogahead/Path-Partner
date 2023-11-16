package com.ssafy.PathPartner.area.service;

import com.ssafy.PathPartner.area.dto.GugunDto;
import com.ssafy.PathPartner.area.dto.SidoDto;
import java.util.List;

public interface AreaService {
  List<SidoDto> getSidoCode();

  List<GugunDto> getGunguCode();
}
