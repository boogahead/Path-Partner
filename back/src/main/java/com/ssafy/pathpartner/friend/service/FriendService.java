package com.ssafy.pathpartner.friend.service;

import com.ssafy.pathpartner.friend.dto.FriendDto;
import com.ssafy.pathpartner.friend.dto.FriendInfoDto;
import com.ssafy.pathpartner.user.exception.InvalidInputException;
import java.sql.SQLException;
import java.util.List;

public interface FriendService {

  List<FriendInfoDto> searchAllMyFriend(String uuid) throws SQLException, InvalidInputException;

  List<FriendInfoDto> searchAllMyFriendRequest(String uuid) throws SQLException, InvalidInputException;
  List<FriendInfoDto> searchAllMyFriendRequestReceived(String uuid) throws SQLException, InvalidInputException;
  Boolean createFriendRequest(FriendDto friendDto) throws SQLException, InvalidInputException;

  Boolean deleteFriend(FriendDto friendDto) throws SQLException, InvalidInputException;

  Boolean updateFriend(FriendDto friendDto) throws SQLException, InvalidInputException;


}
