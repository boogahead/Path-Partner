package com.ssafy.pathpartner.user.repository;


import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  int insertUser(UserDto userDto) throws SQLException;

  int updateUser(UpdateUserDto updateUserDto) throws SQLException;

  int deleteUser(String uuid) throws SQLException;

  Optional<UserDto> selectUserById(String id) throws SQLException;

  Optional<UserDto> selectUserByUuid(String uuid) throws SQLException;

}
