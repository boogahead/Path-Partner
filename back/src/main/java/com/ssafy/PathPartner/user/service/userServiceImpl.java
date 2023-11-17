package com.ssafy.pathpartner.user.service;


import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.repository.userDao;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class userServiceImpl implements userService {

  private final userDao userDao;

  @Autowired
  public userServiceImpl(userDao userDao) {
    this.userDao = userDao;
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int registUser(UserDto userDto) throws SQLException {

    if (userDao.selectUserById(userDto.getId()) != null) {
      return 0;
    } else {
      //if (userDto.getRoles() == 0 || userDto.getRoles().isEmpty()) {
      //}

      log.debug("password encode");
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(userDto.getPassword());

      userDto.setPassword(encodedPwd);
    }
    return userDao.createUser(userDto);

  }

  @Override
  public UserDto login(String userId, String userPass) throws SQLException {
    UserDto loginMember = userDao.selectUserById(userId);

    if (loginMember != null) {
      boolean isValid = BCrypt.checkpw(userPass, loginMember.getPassword());

      if (isValid) {
        return loginMember;
      }
    }
    return null;
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int modifyUser(String password,String Nickname)
      throws SQLException {
    UserDto tmp= userDao.selectUserById("boogahead");
    /* TODO : ^ 위에 userDto.getId() 는 JWT 로 토큰을 받아와야 한다*/

    //tmp.setId(loginMember.getId());
    /*
    if (password == null || password.isEmpty()) {
      tmp.setPassword(loginMember.getPassword());
    }

    if (userDto.getNickname() == null || userDto.getNickname().isEmpty()) {
      userDto.setNickname(loginMember.getNickname());
    }
     */
    if (password != null && !password.isEmpty()) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(password);
      tmp.setPassword(encodedPwd);
    }

    if (Nickname != null && !Nickname.isEmpty()) {
      tmp.setNickname(Nickname);
    }
    return userDao.updateUser(tmp);
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int deleteUser(String uid) throws SQLException {
    return userDao.deleteUser(uid);
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
}
