package org.zerock.fmt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CriteriaComment;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserProfileVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.CommentService;
import org.zerock.fmt.service.ProfileService;

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
	
	@Setter(onMethod_= @Autowired)
	private ProfileService profileService; 
	
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
	public Map<String, Object> commentList(@PathVariable("a_number") int a_number,
											@PathVariable("currPage") int currPage, 
											Model model) throws ControllerException {
		log.trace("해당 답변글의 댓글 목록 출력");
		
		CriteriaComment criteria = new CriteriaComment(a_number, currPage, 5);
		
		try {
			List<CommentVO> commentList = this.commentService.getComment(criteria);
			//-------
			
			
			//--------------------------프로필 사진 가져오기2
//			List<List> profileList = new ArrayList<>();
//			
//			commentList.forEach(e -> {
//				try {
//					List<UserProfileVO> profile = this.profileService.getUserNaP(e.getUser_email());
//					profileList.add(profile);
//				} catch (ServiceException e1) { ;; }
//			});
//			model.addAttribute("profileList", profileList); // 프로필 추가해서 올린 댓글
			//-----------------------------------------------
			
			//-------
			int commentCnt = this.commentService.commentCount(a_number);
			log.info("commentCnt: {}", commentCnt);
			
			Map<String, Object> map = new HashMap<>();
			map.put("list", commentList); // 페이징된 댓글 목록 (5개)
			map.put("commentCnt", commentCnt); // 댓글의 총 개수
			
			//map.put("profileList", profileList); // 프로필 추가한 댓글 리스트

			return map;
			
		} catch (ServiceException e) { throw new ControllerException(e); }
	} // commentList

	
	@RequestMapping(value="modify/{cno}", method={ RequestMethod.PUT, RequestMethod.PATCH },
					consumes="application/json", produces={ MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modifyComment(@RequestBody CommentDTO dto, @PathVariable("cno") String cno) throws ControllerException {
		log.trace("댓글 수정");
		
		int cnum = Integer.parseInt(cno);
		dto.setCm_number(cnum);
		log.info("cno: {}", cno);
		log.info("dto: {}", dto);
		
		try {
			boolean result = this.commentService.updateComment(dto);
			
			return result == true ? new ResponseEntity<>("댓글 수정 완료", HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) { throw new ControllerException(e); }
	} // modifyComment
	
	
	@DeleteMapping(value="/delete/{cno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> deleteComment(@PathVariable("cno") String cno) throws ControllerException {
		log.trace("댓글 삭제");
		log.info("삭제 댓글 번호: {}", cno);
		
		try {
			boolean result = this.commentService.deleteComment(cno);
			return result == true ? new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK)
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { throw new ControllerException(e); }
		
	} // deleteComment
	
	
	// 해당 댓글 조회
	@GetMapping(value="/{cno}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentVO> getComment(@PathVariable("cno") String cno ) throws ControllerException {
        log.trace("해당 댓글 조회");
        log.info("댓글 번호 : " + cno);
        
        try {
        	CommentVO vo = this.commentService.getOneComment(cno);
        	log.info("vo: {}", vo);
        
        	return new ResponseEntity<>(vo, HttpStatus.OK);
        	
        } catch (Exception e) { throw new ControllerException(e); }
    } // getComment
	

} // end class
