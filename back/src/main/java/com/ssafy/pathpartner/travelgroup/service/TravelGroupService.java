package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import java.sql.SQLException;
public interface TravelGroupService {
    void createTravelGroup(TravelGroupDto travelGroupDto) throws SQLException;
    void deleteTravelGroup(String travelGroupId) throws SQLException;
    void leaveTravelGroup(String travelGroupId) throws SQLException;
    int checkGroupMaster(String uid) throws SQLException;
    TravelGroupDto getTravelGroupinfo(String travelGroupId) throws SQLException;
}
