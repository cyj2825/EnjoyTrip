package com.ssafy.enjoytrip.recommend.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.recommend.model.dto.AutumnDto;
import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.SummerDto;

@Mapper
public interface AutumnDao {
	List<AutumnDto> listAutumnPlace() throws SQLException;
	AutumnDto getAutumnPlace(int autumnNo) throws SQLException;
}