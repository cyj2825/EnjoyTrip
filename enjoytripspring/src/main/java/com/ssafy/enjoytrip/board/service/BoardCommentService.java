package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.dto.BoardCommentDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardCommentService {
	List<BoardCommentDto> listComment(Map<String, String> map);
	PageNavigation makePageNavigation(Map<String, String> map);
	void writeComment(BoardCommentDto boardCommentDto);
	void modifyComment(BoardCommentDto boardCommentDto);
	void deleteComment(int commentNo); 
}
