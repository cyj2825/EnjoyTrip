package com.ssafy.enjoytrip.recommend.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.SummerDto;

@Mapper
public interface SummerDao {
	List<SummerDto> listSummerPlace() throws SQLException;
	SummerDto getSummerPlace(int summerNo) throws SQLException;
}