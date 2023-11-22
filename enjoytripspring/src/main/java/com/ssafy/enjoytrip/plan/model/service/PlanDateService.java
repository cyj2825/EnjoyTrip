package com.ssafy.enjoytrip.plan.model.service;

import com.ssafy.enjoytrip.plan.model.dto.PlanDateDetailDto;

import java.util.List;

public interface PlanDateService {
    void insertPlanDate(PlanDateDetailDto dto);

    List<PlanDateDetailDto> getDateList(int planNo);
}
