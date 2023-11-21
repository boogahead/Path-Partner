package com.ssafy.pathpartner.user.service;

import com.ssafy.pathpartner.user.dto.ResetPasswordDto;
import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  boolean createUser(SignUpDto signUpDto) throws SQLException, IOException;

  boolean deleteUser(String uuid) throws SQLException;

  boolean updateUser(UpdateUserDto updateUserDto) throws SQLException, IOException;

  UserInfoDto searchUserByUuid(String uuid) throws SQLException, UserNotFoundException;

  UserInfoDto searchUserById(String id) throws SQLException, UserNotFoundException;

  String searchUserByEmail(String email) throws SQLException, UserNotFoundException;

  List<UserInfoDto> searchAllUserByNickname(String nickname) throws SQLException;

  String resetPassword(ResetPasswordDto resetPasswordDto)
      throws SQLException, UserNotFoundException;

}
