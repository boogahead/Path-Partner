package com.ssafy.pathpartner.travelgroup.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
public interface GroupInviteDao {
    void createGroupInvite(GroupInviteDto groupInviteDto) throws SQLException;
    void deleteGroupInvite(GroupInviteDto groupInviteDto) throws SQLException;
    void acceptGroupInvite(TravelGroupDto travelGroupDto) throws SQLException;
    List<String> checkGroupInvite(String inviteTo) throws SQLException;
    List<String> getPendingInviteList(String groupId) throws SQLException;
    void cancelGroupInvite(Map<String,Object> params) throws SQLException;
}
