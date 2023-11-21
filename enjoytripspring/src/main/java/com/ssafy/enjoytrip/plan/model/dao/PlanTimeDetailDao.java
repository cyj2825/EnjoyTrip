package com.ssafy.enjoytrip.plan.model.dao;

import com.ssafy.enjoytrip.plan.model.dto.PlanTimeDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
@Mapper
public interface PlanTimeDetailDao {
    void insertPlanTime(PlanTimeDetailDto timeDto) throws SQLException;
}
