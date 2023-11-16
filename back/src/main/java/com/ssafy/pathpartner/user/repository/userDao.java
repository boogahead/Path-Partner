package com.ssafy.pathpartner.user.repository;

import com.ssafy.pathpartner.user.dto.userDto;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userDao {

  int createUser(userDto userDto) throws SQLException;

  userDto selectMemberById(String userId) throws SQLException;

  int updateMember(userDto userDto) throws SQLException;

  int updateMemberPassword(userDto userDto) throws SQLException;

  int deleteMember(String uid) throws SQLException;

  userDto selectMemberByIdAndName(userDto userDto) throws SQLException;
}
