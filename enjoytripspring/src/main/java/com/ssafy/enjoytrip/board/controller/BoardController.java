package com.ssafy.enjoytrip.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.service.BoardService;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/board") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함
@Api(value="Board REST-ful위한 API", description = "정보 공유 게시판")
@CrossOrigin({"*"})   // 다른 서버에서 AJax 
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private BoardService boardService;

	private static final String SUCCESS = "success";

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * ResponseEntity 응답 코드 + 응답 데이터를 전송하기 위한 객체
	 */
	
	// ${root}/board/insertReview
    @ApiOperation(value="여행 후기 글 등록", notes = "여행 후기 글을 등록한다.")
	@ApiResponse(code = 200, message="success")
    @PostMapping
    public ResponseEntity<String> insertReviewBoard(@RequestBody BoardDto bDto, @ApiIgnore HttpSession session) {
        System.out.println(bDto);
        boardService.writeArticle(bDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @ApiOperation(value="여행 후기 글 조회", notes = "여행 후기 글을 조회한다.")
   	@ApiResponse(code = 200, message="success")
    @GetMapping("/{articleNo}")
    public ResponseEntity<?> getReviewBoard(@PathVariable int articleNo, HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("get.....articleNo:{}", articleNo);
    	
        // 조회수 로직
		Cookie[] cookies = request.getCookies();
		Cookie oldCookie = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("boardView")) {
					oldCookie = cookie;
				}
			}
		}
		if (oldCookie != null) {
			if (!oldCookie.getValue().contains("["+ Integer.toString(articleNo) +"]")) {
				boardService.updateHit(articleNo);
				oldCookie.setValue(oldCookie.getValue() + "_[" + articleNo + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24); 							// 쿠키 시간
				response.addCookie(oldCookie);
			}
		} else {
			boardService.updateHit(articleNo);
			Cookie newCookie = new Cookie("boardView", "[" + articleNo + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24); 								// 쿠키 시간
			response.addCookie(newCookie);
		}
		
		BoardDto board = boardService.getArticle(articleNo);
    	System.out.println(board);
    	
		if(board != null) {
        	return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="여행 후기 글 수정", notes = "여행 후기 글을 수정한다.")
   	@ApiResponse(code = 200, message="success")
    @PutMapping
    public ResponseEntity<String> updateReviewBoard(@RequestBody BoardDto bDto, @ApiIgnore HttpSession session) {

        MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
        System.out.println(mDto);
        bDto.setUserId("ssafy");
        System.out.println(bDto);
        boardService.modifyArticle(bDto);
        
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @ApiOperation(value="여행 후기 글 삭제", notes = "여행 후기 글을 삭제한다.")
   	@ApiResponse(code = 200, message="success")
    @DeleteMapping("/{articleNo}")
    public ResponseEntity<String> deleteReviewBoard(@PathVariable int articleNo) {
    	logger.debug("get.....articleNo:{}", articleNo);
    	boardService.deleteArticle(articleNo);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @ApiOperation(value="여행 후기 리스트", notes = "여행 후기 리스트를 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping
    public ResponseEntity<?> listReviewsBoard(@RequestParam Map<String, String> data) {
        logger.debug("boardDto...................{}:", data);
        List<BoardDto> boardDto = boardService.listArticle(data);
        logger.debug("boardDto......................{}: ", boardDto);
        if(boardDto!=null) {
            return new ResponseEntity<List<BoardDto>>(boardDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value="나의 여행 후기 리스트", notes = "나의 여행 후기 리스트를 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping("/list/{userId}")
    public ResponseEntity<?> listMyReviewsBoard(@PathVariable String userId) {
        List<BoardDto> boardDto = boardService.listMyArticle(userId);
        logger.debug("boardDto......................{}: ", boardDto);
        if(boardDto!=null) {
            return new ResponseEntity<List<BoardDto>>(boardDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
}