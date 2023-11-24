package com.ssafy.enjoytrip.member.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="Member Dto" ,description="사용자를 구성하는 DTO")
@Data
public class MemberDto {
	@ApiModelProperty(value="사용자 ID", example="Yejin")
	private String userId;
	@ApiModelProperty(value="사용자 이름", example="조예진")
	private String userName;
	@ApiModelProperty(value="사용자 비밀번호", example="1234")
	private String userPwd;
	@ApiModelProperty(value="이메일 ID", example="c123")
	private String emailId;
	@ApiModelProperty(value="이메일 도메인", example="naver.com")
	private String emailDomain;
	@ApiModelProperty(value="회원가입 날짜", example="2023-10-10")
	private String joinDate;
	@ApiModelProperty(value = "refreshToken")
	private String refreshToken;
}
