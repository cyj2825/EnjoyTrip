package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.dto.BoardCommentDto;
import com.ssafy.enjoytrip.board.service.BoardCommentService;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController // Controller 내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/comment") // 요청하는 자원(Domain)명을 붙인다. ==> /book이 이미 있어서 /rest로 함
@Api(value="BoardComment REST-ful위한 API", description = "정보 공유 게시판 댓글")
@CrossOrigin({"*"})   // 다른 서버에서 AJax 
public class BoardCommentController {
	private Logger logger = LoggerFactory.getLogger(BoardCommentController.class);
	private BoardCommentService boardCommentService;
	
	private static final String SUCCESS = "success";
	
	public BoardCommentController(BoardCommentService boardCommentService) {
		this.boardCommentService = boardCommentService;
	}
	
	@ApiOperation(value="정보 공유 댓글 등록", notes="여행 정보 리뷰 댓글을 등록한다.")
	@ApiResponse(code = 200, message="success")
	@PostMapping
	public ResponseEntity<String> insertComment(@RequestBody BoardCommentDto bcDto, @ApiIgnore HttpSession session) {

        MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
        //System.out.println(mDto);
        // userId, subject, content 입력
        //bDto.setUserId("ssafy");
        System.out.println(bcDto);
        boardCommentService.writeComment(bcDto);
        
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
	
	@ApiOperation(value="정보 공유 댓글 수정", notes = "여행 정보 리뷰 댓글을 수정한다.")
   	@ApiResponse(code = 200, message="success")
    @PutMapping
    public ResponseEntity<String> updateComment(@RequestBody BoardCommentDto bcDto, @ApiIgnore HttpSession session) {

        MemberDto mDto = (MemberDto) session.getAttribute("loginUser");
        System.out.println(mDto);
        bcDto.setUserId("ssafy");
        System.out.println(bcDto);
        boardCommentService.modifyComment(bcDto);
        
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @ApiOperation(value="정보 공유 댓글 삭제", notes = "여행 정보 리뷰 댓글을 삭제한다.")
   	@ApiResponse(code = 200, message="success")
    @DeleteMapping("/{articleNo}/{commentNo}")
    public ResponseEntity<String> deleteComment(@PathVariable int articleNo, @PathVariable int commentNo) {
    	logger.debug("get.....commentNo:{}", commentNo);
    	boardCommentService.deleteComment(articleNo, commentNo);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @ApiOperation(value="여행 정보 리뷰 댓글 리스트", notes = "여행 정보 리뷰 댓글들을 보여준다.")
    @ApiResponse(code = 200, message="success")
    @GetMapping("/{articleNo}")
    public ResponseEntity<?> listComments(@PathVariable int articleNo) {
        List<BoardCommentDto> boardCommentDto = boardCommentService.listComment(articleNo);
        logger.debug("boardCommentDto......................{}: ", boardCommentDto);
        if(boardCommentDto!=null) {
            return new ResponseEntity<List<BoardCommentDto>>(boardCommentDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
}