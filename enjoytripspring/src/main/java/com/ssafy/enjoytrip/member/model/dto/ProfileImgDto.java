package com.ssafy.enjoytrip.member.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="ProfileImg Dto" ,description="프로필 이미지를 구성하는 DTO")
@Data
public class ProfileImgDto {
	@ApiModelProperty(value="사용자 ID", example="Yejin")
	private String userId;
	@ApiModelProperty(value = "프로필 이미지")
	private String profileImg;
}