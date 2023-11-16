package com.ssafy.enjoytrip.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.area.dto.GugunDto;
import com.ssafy.enjoytrip.area.dto.SidoDto;
import com.ssafy.enjoytrip.area.repository.AreaDao;

@Service
public class AreaServiceImpl implements AreaService {
	private final AreaDao areaDao;

	@Autowired
	public AreaServiceImpl(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public List<SidoDto> getSidoCode() {
		List<SidoDto> list = areaDao.getSidoCode();
		return list;
	}

	@Override
	public List<GugunDto> getGunguCode() {
		List<GugunDto> list = areaDao.getGunguCode();
		return list;
	}

}