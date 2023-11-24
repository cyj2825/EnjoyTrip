package com.ssafy.enjoytrip.plan.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="PlanDateDetail Dto" ,description="날짜별 계획을 구성하는 DTO")
@Data
public class PlanDateDetailDto {
	@ApiModelProperty(value="날짜 번호", example="0")
	private int dateNo;
	@ApiModelProperty(value="계획 번호", example="0")
	private int planNo;
	@ApiModelProperty(value="여행 날짜", example="2024-01-01")
	private String date;
	@ApiModelProperty(value="날짜별 여행 메모",example="즐거운 여행")
	private String dateMemo;
}