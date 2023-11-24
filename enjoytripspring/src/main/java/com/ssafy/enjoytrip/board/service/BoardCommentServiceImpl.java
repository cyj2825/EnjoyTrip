package com.ssafy.enjoytrip.board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.dao.BoardCommentDao;
import com.ssafy.enjoytrip.board.model.dto.BoardCommentDto;
import com.ssafy.enjoytrip.exception.MyException;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service("BoardCommentService")
@Repository
public class BoardCommentServiceImpl implements BoardCommentService {

	@Autowired
	private BoardCommentDao boardCommentDao;
	
	public BoardCommentServiceImpl(BoardCommentDao boardCommentDao) {
		this.boardCommentDao = boardCommentDao;
	}
	
	@Override
	public List<BoardCommentDto> listComment(int articleNo) {
		try {
			return boardCommentDao.listComment(articleNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("listComment 처리 중 오류 발생!");
		}
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) {
		try {
			PageNavigation pageNavigation = new PageNavigation();

			int naviSize = SizeConstant.NAVIGATION_SIZE;
			int sizePerPage = SizeConstant.LIST_SIZE;
			int currentPage = Integer.parseInt(map.get("pgno"));

			pageNavigation.setCurrentPage(currentPage);
			pageNavigation.setNaviSize(naviSize);
			int totalCount = boardCommentDao.getTotalCommentCount(map);
			pageNavigation.setTotalCount(totalCount);
			int totalPageCount = (totalCount - 1) / sizePerPage + 1;
			pageNavigation.setTotalPageCount(totalPageCount);
			boolean startRange = currentPage <= naviSize;
			pageNavigation.setStartRange(startRange);
			boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
			pageNavigation.setEndRange(endRange);
			pageNavigation.makeNavigator();
			
			return pageNavigation;
		} catch (Exception e) {
			throw new MyException("makePageNavigation 처리 중 오류 발생!");
		}
	}

	@Override
	public void writeComment(BoardCommentDto boardCommentDto) {
		try {
			boardCommentDao.writeComment(boardCommentDto);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new MyException("writeComment 처리 중 오류 발생!");
		}
	}

	@Override
	public void modifyComment(BoardCommentDto boardCommentDto) {
		try {
			boardCommentDao.modifyComment(boardCommentDto);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new MyException("modifyComment 처리 중 오류 발생!");
		}
	}

	@Override
	public void deleteComment(int articleNo, int commentNo) {
		try {
			boardCommentDao.deleteComment(articleNo, commentNo);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new MyException("modifyComment 처리 중 오류 발생!");
		}
	}
}
