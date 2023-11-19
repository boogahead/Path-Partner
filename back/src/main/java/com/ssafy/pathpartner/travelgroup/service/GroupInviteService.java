package com.ssafy.pathpartner.travelgroup.service;

import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import java.sql.SQLException;
import java.util.List;

public interface GroupInviteService {
    void createGroupInvite(String groupId, String inviteTo) throws SQLException;
    void deleteGroupInvite(String groupId) throws SQLException;
    void acceptGroupInvite(String groupId) throws SQLException;
    List<String> checkGroupInvite() throws SQLException;
    List<String> getPendingInviteList(String group_id) throws SQLException;

    void cancelGroupInvite(String groupId, String inviteTo) throws SQLException;
}
