package com.ssafy.enjoytrip.recommend.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.recommend.model.dto.AutumnDto;
import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.SummerDto;
import com.ssafy.enjoytrip.recommend.model.dto.WinterDto;
import com.ssafy.enjoytrip.recommend.model.service.AutumnService;
import com.ssafy.enjoytrip.recommend.model.service.SpringService;
import com.ssafy.enjoytrip.recommend.model.service.SummerService;
import com.ssafy.enjoytrip.recommend.model.service.WinterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/place") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함
@Api(value="Place REST-ful위한 API", description = "계절별 여행지 추천")
@CrossOrigin({"*"})   // 다른 서버에서 AJax 
public class PlaceController {
	private Logger logger = LoggerFactory.getLogger(PlaceController.class);
	private SpringService springService;
    private SummerService summerService;
    private AutumnService autumnService;
    private WinterService winterService;
    private static final String SUCCESS = "success";
    
    public PlaceController(
    		SpringService springService,
    		SummerService summerService,
    		AutumnService autumnService, WinterService winterService) {
        this.springService = springService;
        this.summerService = summerService;
        this.autumnService = autumnService;
        this.winterService = winterService;
    }
    
    @ApiOperation(value="봄 여행지 리스트", notes = "봄 여행지 리스트를 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping("/spring")
    public ResponseEntity<?> listSpringPlace() {
        List<SpringDto> springDto = springService.listSpringPlace();
        logger.debug("springDto......................{}: ", springDto);
        if(springDto!=null) {
            return new ResponseEntity<List<SpringDto>>(springDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="봄 여행지  조회", notes = "봄 여행지를 조회한다.")
   	@ApiResponse(code = 200, message="success")
    @GetMapping("/spring/{springNo}")
    public ResponseEntity<?> getSpringPlace(@PathVariable int springNo) {
    	logger.debug("get.....springNo:{}", springNo);
    	
    	SpringDto springDto = springService.getSpringPlace(springNo);
    	
		if(springDto != null) {
			return new ResponseEntity<SpringDto>(springDto, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="여름 여행지 리스트", notes = "여름 여행지 리스트를 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping("/summer")
    public ResponseEntity<?> listSummerPlace() {
        List<SummerDto> summerDto = summerService.listSummerPlace();
        logger.debug("summerDto......................{}: ", summerDto);
        if(summerDto!=null) {
            return new ResponseEntity<List<SummerDto>>(summerDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="여름 여행지  조회", notes = "여름 여행지를 조회한다.")
   	@ApiResponse(code = 200, message="success")
    @GetMapping("/summer/{summerNo}")
    public ResponseEntity<?> getSummerPlace(@PathVariable int summerNo) {
    	logger.debug("get.....summerNo:{}", summerNo);
    	
    	SummerDto summerDto = summerService.getSummerPlace(summerNo);
    	
		if(summerDto != null) {
			return new ResponseEntity<SummerDto>(summerDto, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="가을 여행지 리스트", notes = "가을 여행지 리스트를 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping("/autumn")
    public ResponseEntity<?> listAutumnPlace() {
        List<AutumnDto> autumnDto = autumnService.listAutumnPlace();
        logger.debug("autumnDto......................{}: ", autumnDto);
        if(autumnDto!=null) {
            return new ResponseEntity<List<AutumnDto>>(autumnDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="가을 여행지  조회", notes = "가을 여행지를 조회한다.")
   	@ApiResponse(code = 200, message="success")
    @GetMapping("/autumn/{autumnNo}")
    public ResponseEntity<?> getAutumnPlace(@PathVariable int autumnNo) {
    	logger.debug("get.....autumnNo:{}", autumnNo);
    	
    	AutumnDto autumnDto = autumnService.getAutumnPlace(autumnNo);
    	
		if(autumnDto != null) {
			return new ResponseEntity<AutumnDto>(autumnDto, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
    @ApiOperation(value="겨을 여행지 리스트", notes = "겨을 여행지 리스트를 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping("/winter")
    public ResponseEntity<?> listWinterPlace() {
        List<WinterDto> winterDto = winterService.listWinterPlace();
        logger.debug("winterDto......................{}: ", winterDto);
        if(winterDto!=null) {
            return new ResponseEntity<List<WinterDto>>(winterDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="겨울 여행지  조회", notes = "겨울 여행지를 조회한다.")
   	@ApiResponse(code = 200, message="success")
    @GetMapping("/winter/{winterNo}")
    public ResponseEntity<?> getWinterPlace(@PathVariable int winterNo) {
    	logger.debug("get.....winterNo:{}", winterNo);
    	
    	WinterDto winterDto = winterService.getWinterPlace(winterNo);
    	
		if(winterDto != null) {
			return new ResponseEntity<WinterDto>(winterDto, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
