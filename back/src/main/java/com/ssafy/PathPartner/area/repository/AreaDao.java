package com.ssafy.PathPartner.area.repository;

import com.ssafy.PathPartner.area.dto.GugunDto;
import com.ssafy.PathPartner.area.dto.SidoDto;
import java.util.List;

public interface AreaDao {

  List<SidoDto> getSidoCode();

  List<GugunDto> getGunguCode();
}
