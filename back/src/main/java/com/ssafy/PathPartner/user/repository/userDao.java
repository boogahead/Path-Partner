package com.ssafy.pathpartner.user.repository;

import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  int createUser(UserDto userDto) throws SQLException;

  UserDto selectUserById(String userId) throws SQLException;

  int updateUser(UserDto userDto) throws SQLException;

  int updateUserPassword(UserDto userDto) throws SQLException;

  int deleteUser(String uid) throws SQLException;

  UserDto selectUserByIdAndName(UserDto userDto) throws SQLException;
}
