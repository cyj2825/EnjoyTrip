package com.ssafy.enjoytrip.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.enjoytrip.board.model.dto.BoardDto;

@Mapper
public interface BoardDao {

	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle(Map<String, String> data) throws SQLException;
	int getTotalArticleCount(Map<String, String> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	List<BoardDto> listMyArticle(String userId) throws SQLException;
}