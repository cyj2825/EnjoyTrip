package com.ssafy.enjoytrip.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {

	List<BoardDto> listArticle(Map<String, String> data);
	PageNavigation makePageNavigation(Map<String, String> map);
	BoardDto getArticle(int articleNo);
	void updateHit(int articleNo);
	void modifyArticle(BoardDto boardDto);
	void deleteArticle(int articleNo);
	void writeArticle(BoardDto boardDto);
	List<BoardDto> listMyArticle(String userId);
}