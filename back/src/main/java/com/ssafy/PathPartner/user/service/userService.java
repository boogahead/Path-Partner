package com.ssafy.PathPartner.user.service;

import com.ssafy.PathPartner.user.dto.userDto;
import java.sql.SQLException;

public interface userService {

	int registMember(userDto userDto) throws SQLException;

	userDto login(String userId, String userPass) throws SQLException;

	int modifyMember(userDto userDto, userDto loginUser) throws SQLException;

	int deleteMember(String uid) throws SQLException;

	userDto searchMemberById(String userId) throws SQLException;

	int updatePassword(userDto userDto) throws SQLException;

}
