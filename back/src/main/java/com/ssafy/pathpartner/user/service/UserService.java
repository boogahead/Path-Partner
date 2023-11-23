package com.ssafy.pathpartner.user.service;

import com.ssafy.pathpartner.user.dto.ResetPasswordDto;
import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  boolean createUser(SignUpDto signUpDto) throws SQLException;

  boolean deleteUser(String uuid) throws SQLException;

  boolean updateUser(UpdateUserDto updateUserDto) throws SQLException;

  UserInfoDto searchUserByUuid(String uuid) throws SQLException, UserNotFoundException;

  UserInfoDto searchUserById(String id) throws SQLException, UserNotFoundException;

  String searchUserByEmail(String email) throws SQLException, UserNotFoundException;

  List<UserInfoDto> searchAllUserByNickname(String nickname,String uuid) throws SQLException;

  String resetPassword(ResetPasswordDto resetPasswordDto)
      throws SQLException, UserNotFoundException;

  Boolean userIdDupCheck(String userId) throws SQLException;
  Boolean nicknameDupCheck(String nickname) throws SQLException;
  Boolean emailDupCheck(String email) throws SQLException;
}
