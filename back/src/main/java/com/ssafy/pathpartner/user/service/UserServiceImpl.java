package com.ssafy.pathpartner.user.service;

import com.ssafy.pathpartner.friend.dto.FriendInfoDto;
import com.ssafy.pathpartner.friend.repository.FriendDao;
import com.ssafy.pathpartner.friend.service.FriendServiceImpl;
import com.ssafy.pathpartner.user.dto.ResetPasswordDto;
import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.friend.dto.FriendDto;
import com.ssafy.pathpartner.user.exception.AlreadyExistsUserException;
import com.ssafy.pathpartner.user.exception.InvalidInputException;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;
import com.ssafy.pathpartner.user.repository.UserDao;
import java.sql.SQLException;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserDao userDao;
  private final PasswordEncoder passwordEncoder;
  private final FriendDao friendDao;

  @Autowired
  public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, FriendDao friendDao) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.friendDao = friendDao;
  }

  public boolean createUser(SignUpDto signUpDto) throws SQLException {
    signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
    userDao.selectUserById(signUpDto.getId()).ifPresent((result) -> {
      throw new AlreadyExistsUserException(String.format("[%s]는 이미 존재하는 id입니다", signUpDto.getId()));
    });
    return userDao.insertUser(UserDto.fromSignUpDto(signUpDto)) > 0;
  }

  public boolean deleteUser(String uuid) throws SQLException {
    //해당 유저가 그룹장인 그룹들 리스트 모으기
    List<String> groupList = userDao.selectGroupListByUuid(uuid);
    //삭제 진행
    userDao.disableForeignKeyChecks();
    int result= userDao.deleteUser(uuid);
    userDao.enableForeignKeyChecks();
    /*
    위에서 모았던 그룹들 리스트 모아서
    for 문 돌면서 각 그룹들 크기를 확인
      만약 크기가 0이면 그냥 삭제
      크기가 1 이상이면 사람들 리스트 구해서 가장 위에 있는 사람을 그룹장으로 설정
     */
    for(String groupid:groupList){
      List<String> memberList = userDao.selectGroupMemberList(groupid);
        if(memberList.size()==0) {
          userDao.deleteGroup(groupid);
        }else{
          Map<String,String> map = new HashMap<>();
            map.put("groupid",groupid);
            map.put("uuid",memberList.get(0));
            userDao.updateGroupLeader(map);
        }
    }
    return result>0;
  }

  public boolean updateUser(UpdateUserDto updateUserDto)
      throws SQLException, InvalidInputException {
    // 내용 검증
    if ((updateUserDto.getPassword() == null && updateUserDto.getNickname() == null)
        || ("".equals(updateUserDto.getPassword()) && "".equals(updateUserDto.getNickname()))
    ) {
      throw new InvalidInputException("입력값이 비었습니다.");
    }

    if (updateUserDto.getPassword() != null && !updateUserDto.getPassword().isEmpty()) {
      updateUserDto.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
    }

    return userDao.updateUser(updateUserDto) > 0;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      log.debug("loadUserByUsername call");
      return userDao.selectUserById(username)
          .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    } catch (SQLException e) {
      log.debug(e.toString());
      return null;
    }
  }

  @Override
  public UserInfoDto searchUserByUuid(String uuid) throws SQLException, UserNotFoundException {
    return UserInfoDto.fromUserDto(userDao.selectUserByUuid(uuid).orElseThrow(
        () -> new UserNotFoundException(
            String.format("[uuid: %s]에 해당하는 사용자 정보를 찾을 수 없습니다.", uuid))));
  }

  @Override
  public String searchUserByEmail(String email) throws SQLException, UserNotFoundException {
    return userDao.selectUserByEmail(email).orElseThrow(
        () -> new UserNotFoundException(
            String.format("[email: %s]에 해당하는 사용자 정보를 찾을 수 없습니다.", email))).getId();
  }

  @Override
  public List<UserInfoDto> searchAllUserByNickname(String nickname,String uuid) throws SQLException {
    if(nickname == null || nickname.isEmpty()) {
      throw new InvalidInputException("입력값이 없습니다.");
    }
    /*
    친구이거나 친구신청이 오가고있으면 리스트에서 빼기
     */
    List<FriendInfoDto> friendlist=friendDao.selectAllFriend(uuid);
    List<FriendInfoDto> friendRequestlist=friendDao.selectAllFriendRequest(uuid);
    List<FriendInfoDto> friendRequestReceivedlist=friendDao.selectAllFriendRequestReceived(uuid);
    //유저리스트 싹다 불러오기
    List<UserInfoDto> userList=userDao.selectAllUserByNickname(nickname);

    Set<FriendInfoDto> friendSet=new HashSet<>();
    Set<UserInfoDto> userSet=new HashSet<>();
    for(FriendInfoDto i:friendlist){
      friendSet.add(i);
    }
    for(FriendInfoDto i:friendRequestlist){
      friendSet.add(i);
    }
    for(FriendInfoDto i:friendRequestReceivedlist){
      friendSet.add(i);
    }
    for(UserInfoDto i:userList){
      userSet.add(i);
    }
    userSet.removeAll(friendSet);
    List<UserInfoDto> returner=new ArrayList<>();
    for(UserInfoDto i:userSet){
      returner.add(i);
    }

    return returner;
  }

  @Override
  public UserInfoDto searchUserById(String id) throws SQLException, UserNotFoundException {
    return UserInfoDto.fromUserDto(userDao.selectUserById(id).orElseThrow(
        () -> new UserNotFoundException(String.format("[id: %s]에 해당하는 사용자 정보를 찾을 수 없습니다.", id))));
  }


  @Override
  public String resetPassword(ResetPasswordDto resetPasswordDto)
      throws SQLException, UserNotFoundException {
    UserDto userDto = userDao.selectUserByIdAndEmail(resetPasswordDto)
        .orElseThrow(() -> new UserNotFoundException("사용자 정보를 찾을 수 없습니다."));

    // 랜덤 문자열 생성
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;

    String tempPassword = new Random().ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    UpdateUserDto updateUserDto = UpdateUserDto.builder()
        .uuid(userDto.getUuid())
        .password(passwordEncoder.encode(tempPassword))
        .build();

    userDao.updateUser(updateUserDto);

    return tempPassword;
  }
}
