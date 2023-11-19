package com.ssafy.pathpartner.travelgroup.repository;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
@Mapper
public interface TravelGroupDao {
    void createTravelGroup(TravelGroupDto travelGroupDto) throws SQLException;
    void deleteTravelGroup(String travelGroupId) throws SQLException;
    void leaveTravelGroup(Map<String,Object> params) throws SQLException;
    int checkGroupMaster(Map<String,Object> params) throws SQLException;
    TravelGroupDto getTravelGroupInfo(String groupId) throws SQLException;

}
