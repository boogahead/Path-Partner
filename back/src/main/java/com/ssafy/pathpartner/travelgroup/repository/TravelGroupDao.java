package com.ssafy.pathpartner.travelgroup.repository;

import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import java.sql.SQLException;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TravelGroupDao {

  int createTravelGroup(TravelGroupDto travelGroupDto) throws SQLException;

  int createTravelGroupMember(TravelGroupDto travelGroupDto) throws SQLException;

  int deleteTravelGroup(String groupId) throws SQLException;

  int deleteTravelGroupMember(@Param("groupId") String groupId, @Param("uuid") String uuid)
      throws SQLException;

  boolean isGroupMember(String groupId, String uuid) throws SQLException;

  boolean isGroupMaster(String groupId, String uuid) throws SQLException;

  List<String> selectAllMyGroupId(String uuid) throws SQLException;

  List<TravelGroupDto> selectAllMyGroup(String uuid) throws SQLException;

  List<GroupMemberDto> selectAllGroupMember(String groupId) throws SQLException;

  int countGroupMember(String groupId) throws SQLException;

  Optional<TravelGroupDto> selectGroup(String groupId) throws SQLException;

  int kickGroupMember(Map<String,String> param) throws SQLException;
}
