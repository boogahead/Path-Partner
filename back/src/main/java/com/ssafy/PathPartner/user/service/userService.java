package com.ssafy.pathpartner.user.service;

import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;

public interface UserService {

	int registUser(UserDto userDto) throws SQLException;

	UserDto login(String userId, String userPass) throws SQLException;

	int modifyUser(String password,String Nickname) throws SQLException;

	int deleteUser(String uid) throws SQLException;

	UserDto searchUserById(String userId) throws SQLException;

	int updatePassword(UserDto userDto) throws SQLException;

}
