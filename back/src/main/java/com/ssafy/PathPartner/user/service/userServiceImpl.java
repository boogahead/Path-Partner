package com.ssafy.PathPartner.user.service;


import com.ssafy.PathPartner.user.dto.userDto;
import com.ssafy.PathPartner.user.repository.userDao;
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
  public int registMember(userDto userDto) throws SQLException {

    /*if (userDao.selectMemberById(userDto.getUserId()) != null) {
      return 0;
    } else*/ {
      //if (userDto.getRoles() == 0 || userDto.getRoles().isEmpty()) {
      //}

      log.debug("password encode");
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(userDto.getUserPass());

      userDto.setUserPass(encodedPwd);
    }
    return userDao.createUser(userDto);

  }

  @Override
  public userDto login(String userId, String userPass) throws SQLException {
    userDto loginMember = userDao.selectMemberById(userId);

    if (loginMember != null) {
      boolean isValid = BCrypt.checkpw(userPass, loginMember.getUserPass());

      if (isValid) {
        return loginMember;
      }
    }
    return null;
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int modifyMember(userDto userDto, userDto loginMember)
      throws SQLException {

    userDto.setUid(loginMember.getUid());

    if (userDto.getUserPass() == null || userDto.getUserPass().isEmpty()) {
      userDto.setUserPass(loginMember.getUserPass());
    }

    if (userDto.getUserName() == null || userDto.getUserName().isEmpty()) {
      userDto.setUserName(loginMember.getUserName());
    }

    userDto.setUserType(loginMember.getUserType());

    return userDao.updateMember(userDto);
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int deleteMember(String uid) throws SQLException {
    return userDao.deleteMember(uid);
  }

  @Override
  public userDto searchMemberById(String userId) throws SQLException {
    return userDao.selectMemberById(userId);
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int updatePassword(userDto userDto) throws SQLException {
    userDto result = userDao.selectMemberByIdAndName(userDto);

    if (result != null) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(userDto.getUserPass());

      result.setUserPass(encodedPwd);
      return userDao.updateMemberPassword(result);
    }
    return 0;
  }
}
