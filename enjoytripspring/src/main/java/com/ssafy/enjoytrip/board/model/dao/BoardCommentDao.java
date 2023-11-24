package com.ssafy.enjoytrip.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.dto.BoardCommentDto;

@Mapper
public interface BoardCommentDao {
	List<BoardCommentDto> listComment(int articleNo) throws SQLException;
	int getTotalCommentCount(Map<String, String> param) throws SQLException;
	void writeComment(BoardCommentDto boardCommentDto) throws SQLException;
	void modifyComment(BoardCommentDto boardCommentDto) throws SQLException;
	void deleteComment(int articleNo, int commentNo) throws SQLException; 
}