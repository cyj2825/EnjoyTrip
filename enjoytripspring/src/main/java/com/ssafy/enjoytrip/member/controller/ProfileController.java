package com.ssafy.enjoytrip.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.ProfileImgDto;
import com.ssafy.enjoytrip.member.service.MemberService;
import com.ssafy.enjoytrip.member.service.ProfileService;
import com.ssafy.enjoytrip.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(value = "프로필 이미지 API", description = "/profile API")
@CrossOrigin(origins = {"*"}) // 다른 서버에서 Ajax 요청이 와도 서비스 되도록 설정
@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
	
	private Logger logger = LoggerFactory.getLogger(ProfileController.class);
	private ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	
	@ApiOperation(value="프로필 이미지", notes="프로필 이미지를 등록")
	@ApiResponse(code = 200, message="success")
	@PostMapping(value="")
	public ResponseEntity<?> insertImg(@RequestBody ProfileImgDto profileImgDto) {
		logger.debug("dto는?{}", profileImgDto);
		logger.debug("profile.insertImg......................... service:{}", profileService);
		profileService.insertImg(profileImgDto);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@ApiOperation(value="프로필 이미지", notes="프로필 이미지를 조회")
	@ApiResponse(code = 200, message="success")
	@GetMapping(value="/{userId}")
	public ResponseEntity<?> getImg(@PathVariable String userId) {
		logger.debug("userId는?{}", userId);
		logger.debug("profile.getImg......................... service:{}", profileService);
		String url = profileService.getImg(userId);
		return new ResponseEntity<String>(url, HttpStatus.OK);	
	}
	
	@ApiOperation(value="프로필 이미지", notes="프로필 이미지를 수정")
	@ApiResponse(code = 200, message="success")
	@PutMapping
	public ResponseEntity<String> updateImg(@RequestBody ProfileImgDto profileImgDto) {
		logger.debug("dto는?{}", profileImgDto);
		logger.debug("profile.updateImg......................... service:{}", profileService);
		profileService.updateImg(profileImgDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	
	}
}
