package com.ssafy.pathpartner.area.repository;

import com.ssafy.pathpartner.area.dto.GugunDto;
import com.ssafy.pathpartner.area.dto.SidoDto;
import java.util.List;

public interface AreaDao {

  List<SidoDto> selectAllSidoCode();

  List<GugunDto> selectGugunCode(int sidoCode);
}
