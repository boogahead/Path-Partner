package com.ssafy.pathpartner.user.service;

import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  boolean createUser(SignUpDto signUpDto) throws SQLException;
  boolean deleteUser(String uuid) throws SQLException;

  boolean updateUser(UpdateUserDto updateUserDto) throws SQLException;


  UserInfoDto searchUserByUuid(String uuid) throws SQLException, UserNotFoundException;

}
