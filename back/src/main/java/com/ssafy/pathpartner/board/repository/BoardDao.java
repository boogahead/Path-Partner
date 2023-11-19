package com.ssafy.pathpartner.board.repository;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pathpartner.board.dto.BoardDto;
import com.ssafy.pathpartner.board.dto.FileInfoDto;

@Mapper
public interface BoardDao {

    void writeArticle(BoardDto boardDto) throws SQLException;

    void registerFile(BoardDto boardDto) throws Exception;

    List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;

    int getTotalArticleCount(Map<String, Object> param) throws SQLException;

    BoardDto getArticle(int articleNo) throws SQLException;

    void updateHit(int articleNo) throws SQLException;

    void modifyArticle(BoardDto boardDto) throws SQLException;

    void deleteFile(int articleNo) throws Exception;

    void deleteArticle(int articleNo) throws SQLException;

    List<FileInfoDto> fileInfoList(int articleNo) throws Exception;

}
