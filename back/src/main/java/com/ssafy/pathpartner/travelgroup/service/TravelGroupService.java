package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.exception.MasterCanNotLeaveGroupException;
import com.ssafy.pathpartner.travelgroup.exception.UnauthoriedGroupRequestException;
import java.sql.SQLException;
import java.util.List;

public interface TravelGroupService {

  boolean createTravelGroup(TravelGroupDto travelGroupDto) throws SQLException;

  boolean deleteTravelGroup(String groupId, String uuid)
      throws SQLException, UnauthoriedGroupRequestException;

  boolean leaveTravelGroup(String groupId, String uuid)
      throws SQLException, UnauthoriedGroupRequestException, MasterCanNotLeaveGroupException;

  List<GroupMemberDto> searchGroupMember(String groupId) throws SQLException;

  List<TravelGroupDto> searchAllGroup(String uuid) throws SQLException;
}
