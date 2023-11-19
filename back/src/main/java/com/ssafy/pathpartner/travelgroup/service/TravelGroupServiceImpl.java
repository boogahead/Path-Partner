package com.ssafy.pathpartner.travelgroup.service;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.repository.TravelGroupDao;

@Service
@Slf4j
public class TravelGroupServiceImpl implements TravelGroupService {
    private final TravelGroupDao travelGroupDao;
    @Autowired
    public TravelGroupServiceImpl(TravelGroupDao travelGroupDao) {
        this.travelGroupDao = travelGroupDao;
    }
    @Override
    public void createTravelGroup(TravelGroupDto travelGroupDto) throws SQLException {
        travelGroupDao.createTravelGroup(travelGroupDto);
    }
    @Override
    public void deleteTravelGroup(String travelGroupId) throws SQLException {
        /*
        현재 uuid가 방장이 아니면 삭제 못하도록 그냥 리턴시켜버릴거임
        uuid는 나중에 JWT로 변경할것
         */
        String uuid=("testtesttest");
        if(checkGroupMaster(uuid)==0)return;
        travelGroupDao.deleteTravelGroup(travelGroupId);
    }
    @Override
    public void leaveTravelGroup(String travelGroupId) throws SQLException {
        /*
        현재 uuid가 방장이면 그냥 리턴시켜버림
        uuid는 나중에 JWT로 변경할것
         */
        String uuid=("testtesttest");
        if(checkGroupMaster(uuid)==1)return;
        Map<String,Object> params=new HashMap<>();
        params.put("uuid",uuid);
        params.put("groupId",travelGroupId);
        travelGroupDao.leaveTravelGroup(params);
    }
    @Override
    public int checkGroupMaster(String groupId) throws SQLException {
        /*
        JWT 필요
         */
        String uuid=("testtesttest");
        Map<String,Object> params=new HashMap<>();
        params.put("uuid",uuid);
        params.put("groupId",groupId);
        return travelGroupDao.checkGroupMaster(params);
    }

    @Override
    public TravelGroupDto getTravelGroupinfo(String travelGroupId) throws SQLException {
        return travelGroupDao.getTravelGroupInfo(travelGroupId);
    }


}
