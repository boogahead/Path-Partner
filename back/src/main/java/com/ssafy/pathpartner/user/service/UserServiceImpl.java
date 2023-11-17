package com.ssafy.pathpartner.user.service;

import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.exception.AlreadyExistsUserException;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;
import com.ssafy.pathpartner.user.repository.UserDao;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserDao userDao;
  PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean createUser(SignUpDto signUpDto) throws SQLException {
    signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
    userDao.selectUserById(signUpDto.getId()).ifPresent((result) -> {
      throw new AlreadyExistsUserException(String.format("[%s]는 이미 존재하는 id입니다", signUpDto.getId()));
    });
    return userDao.insertUser(UserDto.fromSignUpDto(signUpDto)) > 0;
  }

  public boolean deleteUser(String uuid) throws SQLException {
    return userDao.deleteUser(uuid) > 0;
  }

  public boolean updateUser(UpdateUserDto updateUserDto) throws SQLException {
    return userDao.updateUser(updateUserDto) > 0;
  }

  @Override
  public UserDto searchUserById(String userId) throws SQLException {
    return userDao.selectUserById(userId);
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int updatePassword(UserDto userDto) throws SQLException {
    UserDto result = userDao.selectUserByIdAndName(userDto);

    if (result != null) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(userDto.getPassword());

      result.setPassword(encodedPwd);
      return userDao.updateUserPassword(result);
    }
    return 0;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  @Override
  public UserInfoDto searchUserByUuid(String uuid) throws SQLException, UserNotFoundException {
    return UserInfoDto.fromUserDto(userDao.selectUserByUuid(uuid).orElseThrow(
        () -> new UserNotFoundException(String.format("[%s]에 해당하는 사용자 정보를 찾을 수 없습니다.", uuid))));
  }
}
