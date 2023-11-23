package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.exception.MasterCanNotLeaveGroupException;
import com.ssafy.pathpartner.travelgroup.exception.UnauthoriedGroupRequestException;
import java.sql.SQLException;
import java.util.List;

public interface TravelGroupService {

  boolean createTravelGroup(TravelGroupDto travelGoupDto) throws SQLException;

  boolean deleteTravelGroup(String groupId, String uuid)
      throws SQLException, UnauthoriedGroupRequestException;

  boolean leaveTravelGroup(String groupId, String uuid)
      throws SQLException, UnauthoriedGroupRequestException, MasterCanNotLeaveGroupException;

  List<GroupMemberDto> searchGroupMember(String groupId) throws SQLException;

  List<TravelGroupDto> searchAllGroup(String uuid) throws SQLException;

  boolean kickmember(String groupId, String uuid, String curUserUuid) throws SQLException;

  boolean inviteGroupMember(TravelGroupDto travelGroupDto, String uuid) throws SQLException;


  boolean cancelInviteGroupMember(TravelGroupDto travelGroupDto, String uuid) throws SQLException;

  boolean acceptInvite(TravelGroupDto travelGroupDto) throws SQLException;

    boolean rejectInvite(TravelGroupDto travelGroupDto) throws SQLException;

List<TravelGroupDto> receivedInviteList(String uuid) throws SQLException;

List<GroupMemberDto> sentInviteList(String groupId) throws SQLException;


}
