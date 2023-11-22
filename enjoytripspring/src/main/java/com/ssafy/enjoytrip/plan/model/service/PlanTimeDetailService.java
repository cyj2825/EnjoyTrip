package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.dto.PlanTimeDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface PlanTimeDetailService {
    void insertTime(PlanTimeDetailDto dto);
    List<PlanTimeDetailDto> getTimeList(int dateNo);
}
