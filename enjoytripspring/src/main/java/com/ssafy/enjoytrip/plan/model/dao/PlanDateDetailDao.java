package com.ssafy.enjoytrip.plan.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.plan.model.dto.PlanDateDetailDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanDateDetailDao {
    void insertPlanDate(PlanDateDetailDto dto) throws SQLException;
    List<PlanDateDetailDto> getDateList(int planNo) throws SQLException;
}
