package com.ssafy.pathpartner.friend.repository;

import com.ssafy.pathpartner.friend.dto.FriendDto;
import com.ssafy.pathpartner.friend.dto.FriendInfoDto;
import com.ssafy.pathpartner.user.exception.InvalidInputException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FriendDao {

  int insertFriend(FriendDto friendDto) throws SQLException;

  int updateFriend(FriendDto friendDto) throws SQLException;

  int deleteFriend(FriendDto friendDto) throws SQLException;

  List<FriendInfoDto> selectAllFriend(String uuid) throws SQLException;

  List<FriendInfoDto> selectAllFriendRequest(String uuid) throws SQLException;

  List<FriendInfoDto> selectAllFriendRequestReceived(String uuid) throws SQLException;

  List<FriendDto> selectFriendFrom(String uuid) throws SQLException;

  List<FriendDto> selectFriendTo(String uuid) throws SQLException;
}
