package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import com.ssafy.pathpartner.travelgroup.exception.TravelGroupNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.repository.TravelGroupDao;
import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.repository.GroupInviteDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class GroupInviteServiceImpl implements GroupInviteService {

  private final GroupInviteDao groupInviteDao;
  private final TravelGroupDao travelGroupDao;

  @Autowired
  public GroupInviteServiceImpl(GroupInviteDao groupInviteDao, TravelGroupDao travelGroupDao) {
    this.groupInviteDao = groupInviteDao;
    this.travelGroupDao = travelGroupDao;
  }

  @Override
  public boolean createGroupInvite(GroupInviteDto groupInviteDto) throws SQLException {
    return groupInviteDao.createGroupInvite(groupInviteDto) > 0;
  }

  @Override
  public boolean deleteGroupInvite(GroupInviteDto groupInviteDto) throws SQLException {
    return groupInviteDao.deleteGroupInvite(groupInviteDto) > 0;
  }

  @Override
  @Transactional
  public boolean acceptGroupInvite(GroupInviteDto groupInviteDto) throws SQLException, TravelGroupNotFoundException {
    deleteGroupInvite(groupInviteDto);
    TravelGroupDto travelGroupDto = travelGroupDao.selectGroup(groupInviteDto.getGroupId())
        .orElseThrow(() -> new TravelGroupNotFoundException("해당 그룹을 찾을 수 없습니다."));

    travelGroupDto.setGroupMaster(false);
    travelGroupDto.setUuid(groupInviteDto.getInviteTo());
    return travelGroupDao.createTravelGroupMember(travelGroupDto) > 0;
  }

  @Override
  public List<GroupInviteDto> searchGroupInvite(String uuid) throws SQLException {
    return groupInviteDao.selectGroupInvite(uuid);
  }

  @Override
  public List<GroupMemberDto> searchAllPendingInvite(String groupId) throws SQLException {
    return groupInviteDao.selectAllPendingInvite(groupId);
  }

}
