package com.ssafy.enjoytrip.plan.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.plan.model.dto.PlanDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface PlanService {
	int writePlan(PlanDto planDto);
	List<PlanDto> listPlan(Map<String, Object> param);
	PlanDto getPlan(int plan_no);
	void modifyPlan(PlanDto planDto);
	void deletePlan(int plan_no);
	PageNavigation makePageNavigation(Map<String, String> map);
}
