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
import com.ssafy.enjoytrip.member.service.MemberService;
import com.ssafy.enjoytrip.member.service.ProfileService;
import com.ssafy.enjoytrip.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(value = "회원 관리 API", description = "/user API")
@CrossOrigin(origins = {"*"}) // 다른 서버에서 Ajax 요청이 와도 서비스 되도록 설정
@RestController
@RequestMapping("/user")
@Slf4j
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;
	private JWTUtil jwtUtil;

	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	private static final String SUCCESS = "success";
	private static final String FAILURE = "failure";
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		e.printStackTrace();
		logger.error("member.error...................msg:{}", e.getMessage());
		
		// 에러 메시지가 한글인 경우 깨지므로 한글 처리를 위한 설정
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-type", "application/json; charset=UTF-8");
		String errorMsg = "";
		if (e instanceof Exception)
			errorMsg = e.getMessage();
		else {
			errorMsg = "처리 중 오류 발생";
		}
			return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ApiOperation(value="회원가입", notes="회원 정보를 등록")
	@ApiResponse(code = 200, message="success")
	@PostMapping(value="/register")
	public ResponseEntity<?> registMember(@RequestBody MemberDto memberDto) {
		logger.debug("dto는?{}", memberDto);
		logger.debug("member.regist......................... service:{}", memberService);
		// int num = memberService.idCheck(memberDto.getUserId());
		memberService.joinMember(memberDto);
		
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@ApiOperation(value="아이디 체크", notes="아이디 존재 여부 체크")
	@ApiResponse(code = 200, message="success")
	@GetMapping
	public ResponseEntity<?> idCheck(@RequestParam String userId) {
		logger.debug("userId는?{}", userId);
		int num = memberService.idCheck(userId);
		
		
		logger.debug("이거 나오나??: {}", num);
		if(num == 0) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<Integer>(1, HttpStatus.NO_CONTENT);	
		}
	}
	
	// JWT 추가
	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인")
	@ApiResponse(code = 200, message="success")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		logger.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				logger.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				// todo: 나중에 변경할 것 : Cookie로 변경할 것이다!!!
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환", response = Map.class)
	@ApiResponse(code = 200, message="success")
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("getInfo.............................................1.사용 가능한 토큰!");
			try {
				MemberDto memberDto = memberService.userInfo(userId);
				log.info("getInfo.............................................member:{}",memberDto);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!");
			status = HttpStatus.UNAUTHORIZED;
		}
		log.info("getInfo.............................................status:{}",status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거", response = Map.class)
	@ApiResponse(code = 200, message="success")
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급", response = Map.class)
	@ApiResponse(code = 200, message="success")
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				// refreshtoken을 통해 다시 accesstoken을 받는다
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="회원삭제", notes="아이디로 회원 정보 삭제")
	@ApiResponse(code = 200, message="success")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> removeMember(@RequestParam String userId){
		logger.debug("member.delete............................ Id:{}",userId);
		memberService.deleteMember(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 정보 수정", notes = "회원 정보 수정")
   	@ApiResponse(code = 200, message="success")
    @PutMapping
    public ResponseEntity<String> updateMember(@RequestBody MemberDto mDto) {
		memberService.updateMember(mDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}
