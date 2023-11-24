package com.ssafy.enjoytrip.recommend.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.exception.MyException;
import com.ssafy.enjoytrip.recommend.model.dao.AutumnDao;
import com.ssafy.enjoytrip.recommend.model.dto.AutumnDto;

@Service("AutumnService")
@Repository
public class AutumnServiceImpl implements AutumnService {

	@Autowired
	private AutumnDao autumnDao;
	private Logger logger = LoggerFactory.getLogger(AutumnServiceImpl.class);
	public AutumnServiceImpl(AutumnDao autumnDao) {
		super();
		this.autumnDao = autumnDao;
	}

	@Override
	public List<AutumnDto> listAutumnPlace(){
		try {
			logger.debug("listAutumnPlace..................................:{}");
			return autumnDao.listAutumnPlace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 리스트 조회 중 오류 발생!");
		}
	}
	
	@Override
	public AutumnDto getAutumnPlace(int autumnNo){
		try {
			logger.debug("getAutumnPlace..................................:{}", autumnNo);
			return autumnDao.getAutumnPlace(autumnNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 조회 중 오류 발생!");
		}
	}
}