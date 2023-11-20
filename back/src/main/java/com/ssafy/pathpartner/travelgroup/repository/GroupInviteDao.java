package com.ssafy.pathpartner.travelgroup.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;

public interface GroupInviteDao {

  int createGroupInvite(GroupInviteDto groupInviteDto) throws SQLException;

  int deleteGroupInvite(GroupInviteDto groupInviteDto) throws SQLException;

  List<GroupInviteDto> selectGroupInvite(String uuid) throws SQLException;

  List<GroupInviteDto> selectAllPendingInvite(String groupId) throws SQLException;
}
