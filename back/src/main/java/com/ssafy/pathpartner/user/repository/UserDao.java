package com.ssafy.pathpartner.user.repository;


import com.ssafy.pathpartner.user.dto.ResetPasswordDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  int insertUser(UserDto userDto) throws SQLException;

  int updateUser(UpdateUserDto updateUserDto) throws SQLException;

  int deleteUser(String uuid) throws SQLException;

  Optional<UserDto> selectUserById(String id) throws SQLException;

  Optional<UserDto> selectUserByUuid(String uuid) throws SQLException;

  Optional<UserDto> selectUserByEmail(String email) throws SQLException;

  List<UserInfoDto> selectAllUserByNickname(String nickname) throws SQLException;

  Optional<UserDto> selectUserByIdAndEmail(ResetPasswordDto resetPasswordDto) throws SQLException;

    void disableForeignKeyChecks() throws SQLException;

  void enableForeignKeyChecks() throws SQLException;

    List<String> selectGroupListByUuid(String uuid) throws SQLException;

    List<String> selectGroupMemberList(String groupid) throws SQLException;

    void deleteGroup(String groupid) throws SQLException;

    void updateGroupLeader(Map<String,String> map) throws SQLException;

    List<UserInfoDto> userList() throws SQLException;


}
