package com.ssafy.pathpartner.area.service;

import com.ssafy.pathpartner.area.dto.GugunDto;
import com.ssafy.pathpartner.area.dto.SidoDto;
import java.util.List;

public interface AreaService {

  List<SidoDto> getSidoCode();

  List<GugunDto> getGunguCode();
}
