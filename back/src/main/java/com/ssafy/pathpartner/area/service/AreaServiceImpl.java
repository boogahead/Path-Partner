package com.ssafy.pathpartner.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pathpartner.area.dto.GugunDto;
import com.ssafy.pathpartner.area.dto.SidoDto;
import com.ssafy.pathpartner.area.repository.AreaDao;

@Service
public class AreaServiceImpl implements AreaService {

  private final AreaDao areaDao;

  @Autowired
  public AreaServiceImpl(AreaDao areaDao) {
    this.areaDao = areaDao;
  }

  @Override
  public List<SidoDto> getSidoCode() {
    return areaDao.selectAllSidoCode();
  }

  @Override
  public List<GugunDto> getGunguCode(int sidoCode) {
    return areaDao.selectGugunCode(sidoCode);
  }

}
