package com.ssafy.enjoytrip.plan.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="PlanTimeDetail Dto" ,description="시간별 계획을 구성하는 DTO")
@Data
public class PlanTimeDetailDto {
	@ApiModelProperty(value="시간 번호", example="0")
	private int timeNo;
	@ApiModelProperty(value="날짜 번호", example="0")
	private int dateNo;
	@ApiModelProperty(value="여행 장소", example="오사카")
	private String location;
	@ApiModelProperty(value="시간별 여행 메모",example="즐거운 여행")
	private String timeMemo;
}