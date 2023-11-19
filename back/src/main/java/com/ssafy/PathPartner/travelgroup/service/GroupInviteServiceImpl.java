package com.ssafy.pathpartner.travelgroup.service;
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
@Service
@Slf4j
public class GroupInviteServiceImpl implements GroupInviteService{
    private final GroupInviteDao groupInviteDao;
    private final TravelGroupDao travelGroupDao;
    @Autowired
    public GroupInviteServiceImpl(GroupInviteDao groupInviteDao, TravelGroupDao travelGroupDao) {
        this.groupInviteDao = groupInviteDao;
        this.travelGroupDao = travelGroupDao;
    }

    @Override
    public void createGroupInvite(String groupId, String inviteTo) throws SQLException {
        GroupInviteDto tmp=new GroupInviteDto();
        tmp.setGroupId(groupId);
        tmp.setInviteTo(inviteTo);
        groupInviteDao.createGroupInvite(tmp);
    }

    @Override
    public void deleteGroupInvite(String groupId) throws SQLException {
        String inviteTo=("testtesttest");//테스트용 임시 uuid
        GroupInviteDto tmp=new GroupInviteDto();
        tmp.setGroupId(groupId);
        tmp.setInviteTo(inviteTo);
        groupInviteDao.deleteGroupInvite(tmp);
    }

    @Override
    public void acceptGroupInvite(String groupId) throws SQLException {
        String inviteTo=("testtesttest");//테스트용 임시 uuid
        deleteGroupInvite(groupId);
        TravelGroupDto tmp=travelGroupDao.getTravelGroupInfo(groupId);
        tmp.setUuid(inviteTo);
        tmp.setGroupMaster(false);//group invite table 에서 삭제하고
        groupInviteDao.acceptGroupInvite(tmp);//travel group table에 추가
    }

    @Override
    public List<String> checkGroupInvite() throws SQLException {
        String inviteTo=("testtesttest");//테스트용 임시 uuid
        return groupInviteDao.checkGroupInvite(inviteTo);
    }

    @Override
    public List<String> getPendingInviteList(String groupId) throws SQLException {
        return groupInviteDao.getPendingInviteList(groupId);
    }

    @Override
    public void cancelGroupInvite(String groupId, String inviteTo) throws SQLException {
        Map<String,Object> tmp=new HashMap<>();
        tmp.put("groupId",groupId);
        tmp.put("inviteTo",inviteTo);
        groupInviteDao.cancelGroupInvite(tmp);
    }


}
