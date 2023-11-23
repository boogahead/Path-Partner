package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import com.ssafy.pathpartner.travelgroup.exception.MasterCanNotLeaveGroupException;
import com.ssafy.pathpartner.travelgroup.exception.UnauthoriedGroupRequestException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
  public boolean createTravelGroup(TravelGroupDto travelGroupDto) throws SQLException {
    return travelGroupDao.createTravelGroup(travelGroupDto) > 0;
  }

  @Override
  public boolean deleteTravelGroup(String groupId, String uuid)
      throws SQLException, UnauthoriedGroupRequestException {

    // 그룹 마스터 확인
    if (travelGroupDao.isGroupMaster(groupId, uuid)) {
      return travelGroupDao.deleteTravelGroup(groupId) > 0;
    } else {
      throw new UnauthoriedGroupRequestException("해당 그룹에 삭제 권한이 없습니다.");
    }
  }

  @Override
  public boolean leaveTravelGroup(String groupId, String uuid)
      throws SQLException, UnauthoriedGroupRequestException, MasterCanNotLeaveGroupException {
    // 그룹 마스터 확인
    if (travelGroupDao.isGroupMaster(groupId, uuid)) {
      // 남은 인원이 없으면
      if (travelGroupDao.countGroupMember(groupId) < 2) {
        return deleteTravelGroup(groupId, uuid);
      } else {
        throw new MasterCanNotLeaveGroupException("그룹장은 탈퇴할 수 없습니다.");
      }
    }

    return travelGroupDao.deleteTravelGroupMember(groupId, uuid) > 0;
  }

  @Override
  public List<GroupMemberDto> searchGroupMember(String groupId) throws SQLException {
    return travelGroupDao.selectAllGroupMember(groupId);
  }

  @Override
  public List<TravelGroupDto> searchAllGroup(String uuid) throws SQLException {
    return travelGroupDao.selectAllMyGroup(uuid);
  }

  @Override
  public boolean kickmember(String groupId, String uuid, String curUserUuid) throws SQLException {
    if(!travelGroupDao.isGroupMaster(groupId,curUserUuid))return false;
    Map<String,String> param = new HashMap<>();
    param.put("groupId",groupId);
    param.put("uuid",uuid);
    return travelGroupDao.kickGroupMember(param)>0;
  }


  @Override
  public boolean inviteGroupMember(TravelGroupDto travelGroupDto, String uuid) throws SQLException {
    if(!travelGroupDao.isGroupMaster(travelGroupDto.getGroupId(),uuid))return false;
    return travelGroupDao.inviteGroupMember(travelGroupDto);
  }


  @Override
  public boolean cancelInviteGroupMember(TravelGroupDto travelGroupDto,String uuid) throws SQLException {
    if(!travelGroupDao.isGroupMaster(travelGroupDto.getGroupId(),uuid))return false;
    return travelGroupDao.cancelInviteGroupMember(travelGroupDto);
  }

  @Override
  public boolean acceptInvite(TravelGroupDto travelGroupDto) throws SQLException {
    return travelGroupDao.acceptInvite(travelGroupDto);
  }

  @Override
  public boolean rejectInvite(TravelGroupDto travelGroupDto) throws SQLException {
    return travelGroupDao.rejectInvite(travelGroupDto);
  }

  @Override
  public List<TravelGroupDto> receivedInviteList(String uuid) throws SQLException {
    return travelGroupDao.receivedInviteList(uuid);
  }

  @Override
  public List<GroupMemberDto> sentInviteList(String groupId) throws SQLException {
    return travelGroupDao.sentInviteList(groupId);
  }


}
