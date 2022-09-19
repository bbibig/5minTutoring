package org.zerock.fmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CriteriaComment;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.CommentService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Setter(onMethod_= @Autowired)
	private CommentService commentService; 
	
	// JSON 데이터(댓글)를 전송하고 처리 결과 문자열로 출력
	@PostMapping(value="/new", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> createComment(@RequestBody CommentDTO dto) throws ControllerException {
		log.trace("댓글 등록");
		
		try { 
			boolean result = this.commentService.createComment(dto); 
		
			log.info("CommentDTO : {}", dto);
			log.info("result : {}", result);
		
		return result == true ? new ResponseEntity<>("댓글 등록 완료", HttpStatus.OK)
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		} catch (Exception e) { throw new ControllerException(e); }
	} // createComment
	
	
	@GetMapping(value="/list/{a_number}/{currPage}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentVO>> commentList(@PathVariable("a_number") int a_number,
													@PathVariable("currPage") int currPage) throws ControllerException {
		log.trace("해당 답변글의 댓글 목록 출력");
		
		CriteriaComment criteria = new CriteriaComment(a_number, currPage, 5);
		
		try {
			List<CommentVO> commentList = this.commentService.getComment(criteria);
			// 댓글 총 개수 구하는 mapper 만들고 추가해야할까? (size메소드로 가능한지 확인하기)
			
			return new ResponseEntity<>(commentList, HttpStatus.OK);
			
		} catch (ServiceException e) { throw new ControllerException(e); }
	} // commentList
	
	
//	@PostMapping("/commentList/{currPage}/{a_number}")
//	public Map<String, Object> commentList(@PathVariable("currPage") Integer currPage,
//			@PathVariable("a_number") Integer a_number, Model model) throws ControllerException {
//		log.trace("댓글 목록 출력");
//		
//		CriteriaComment criteria = new CriteriaComment(a_number, currPage, 5);
//		
//		try {
//			List<CommentVO> commentList = this.commentService.getComment(criteria);
//			// 댓글 총 개수 구하는 mapper 만들고 추가해야할까? (size메소드로 가능한지 확인하기)
//			
//			Map<String, Object> map = new HashMap<>();
//			map.put("list", commentList);
//			map.put("total", commentList.size());
//			
//			return map;
//			
//		} catch (ServiceException e) { throw new ControllerException(e); }
//	} // commentList
	
	// 댓글 조회 핸들러는 안필요할 듯.
	
	@GetMapping(value="/delete/{cno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> deleteComment(@PathVariable("cno") String cno) throws ControllerException {
		log.trace("댓글 삭제");
		log.info("삭제 댓글 번호: {}", cno);
		
		try {
			boolean result = this.commentService.deleteComment(cno);
			return result == true ? new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { throw new ControllerException(e); }
		
	} // deleteComment
	
	
	// Map<String, Object>로 처리를 해볼까 함.
//	@PostMapping(method)
//	public ResponseEntity<String> modifyComment(@RequestBody CommentDto dto, @PathVariable("cno") int cno) {
//		
//		
//		
//	} // modifyComment
	
	

} // end class
