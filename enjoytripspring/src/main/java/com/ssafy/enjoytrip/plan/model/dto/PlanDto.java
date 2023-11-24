package com.ssafy.enjoytrip.plan.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value="Plan Dto" ,description="계획을 구성하는 DTO")
@Data
public class PlanDto {
	@ApiModelProperty(value="계획 번호", example="0")
	private int planNo;
	@ApiModelProperty(value="사용자 ID", example="Yejin")
	private String userId;
	@ApiModelProperty(value="제목",example="테스트 제목")
	private String title;
	@ApiModelProperty(value="여행 목적",example="가족 여행")
	private String purpose;
	@ApiModelProperty(value="여행 메모",example="즐거운 여행")
	private String memo;
	@ApiModelProperty(value="여행 시작 날짜", example="2024-01-01")
	private String startDate;
	@ApiModelProperty(value="여행 종료 날짜", example="2024-02-01")
	private String endDate;
}