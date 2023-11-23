package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.exception.TravelGroupNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface GroupInviteService {

  boolean createGroupInvite(GroupInviteDto groupInviteDto) throws SQLException;

  boolean deleteGroupInvite(GroupInviteDto groupInviteDto) throws SQLException;

  boolean acceptGroupInvite(GroupInviteDto groupInviteDto) throws SQLException, TravelGroupNotFoundException;

  List<GroupInviteDto> searchGroupInvite(String uuid) throws SQLException;

  List<GroupMemberDto> searchAllPendingInvite(String groupId) throws SQLException;

}
