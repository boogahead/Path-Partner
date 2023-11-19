package com.ssafy.pathpartner.friend.service;

import com.ssafy.pathpartner.friend.dto.FriendDto;
import com.ssafy.pathpartner.friend.dto.FriendInfoDto;
import com.ssafy.pathpartner.friend.repository.FriendDao;
import com.ssafy.pathpartner.user.exception.InvalidInputException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

  private final FriendDao friendDao;

  @Autowired
  public FriendServiceImpl(FriendDao friendDao) {
    this.friendDao = friendDao;
  }

  @Override
  public List<FriendInfoDto> searchAllMyFriend(String uuid) throws SQLException {
    if (uuid == null || uuid.isEmpty()) {
      throw new InvalidInputException("입력값이 없습니다.");
    }
    return friendDao.selectAllFriend(uuid);
  }

  @Override
  public List<FriendInfoDto> searchAllMyFriendRequest(String uuid)
      throws SQLException, InvalidInputException {
    if (uuid == null || uuid.isEmpty()) {
      throw new InvalidInputException("입력값이 없습니다.");
    }
    return friendDao.selectAllFriendRequest(uuid);
  }

  @Override
  public List<FriendInfoDto> searchAllMyFriendRequestReceived(String uuid)
      throws SQLException, InvalidInputException {
    if (uuid == null || uuid.isEmpty()) {
      throw new InvalidInputException("입력값이 없습니다.");
    }
    return friendDao.selectAllFriendRequestReceived(uuid);
  }

  @Override
  public Boolean createFriendRequest(FriendDto friendDto)
      throws SQLException, InvalidInputException {
    return friendDao.insertFriend(friendDto) > 1;
  }

  @Override
  public Boolean deleteFriend(FriendDto friendDto)
      throws SQLException, InvalidInputException {
    return friendDao.deleteFriend(friendDto) > 1;
  }

  @Override
  public Boolean updateFriend(FriendDto friendDto) throws SQLException, InvalidInputException {
    return friendDao.updateFriend(friendDto) > 0;
  }
}
