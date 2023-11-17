package com.ssafy.pathpartner.user.repository;

<<<<<<< HEAD:back/src/main/java/com/ssafy/pathpartner/user/repository/userDao.java
import com.ssafy.pathpartner.user.dto.userDto;
=======
import com.ssafy.pathpartner.user.dto.UserDto;
>>>>>>> bbc6bd2b08c1c68c1c97f5c25ad2cf6d819ac5a5:back/src/main/java/com/ssafy/PathPartner/user/repository/userDao.java
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userDao {

  int createUser(UserDto userDto) throws SQLException;

  UserDto selectUserById(String userId) throws SQLException;

  int updateUser(UserDto userDto) throws SQLException;

  int updateUserPassword(UserDto userDto) throws SQLException;

  int deleteUser(String uid) throws SQLException;

  UserDto selectUserByIdAndName(UserDto userDto) throws SQLException;
}
