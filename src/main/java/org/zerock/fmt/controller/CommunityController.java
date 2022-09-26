package org.zerock.fmt.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.domain.CommunityPageDTO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaCommunity;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserProfileVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.CommentService2;
import org.zerock.fmt.service.CommunityService;
import org.zerock.fmt.service.ProfileService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/community")
@Controller
public class CommunityController implements InitializingBean{
	
	@Setter(onMethod_ = @Autowired)
	private CommunityService communityService;
	
	@Setter(onMethod_ = @Autowired)
	private CommentService2 commentService2;
	
	@Setter(onMethod_= @Autowired)
	private ProfileService profileService;
	
	
	//전체게시글 조회
	@GetMapping
	public String communityPage(Model model, CriteriaCommunity page) throws ControllerException {
		log.trace("communityPage()invoked");
		
		try {
			
			List<CommunityVO> list = this.communityService.selectAllList(page);
			
			model.addAttribute("_LIST_", list);	
//			----------------------------------------------------- 전체게시글 리스트
			CommunityPageDTO pageDto = new CommunityPageDTO(page, this.communityService.allCount(page));
			model.addAttribute("_COMMUNITYPAGE_", pageDto);
			
//			---------------------------------------------------- 페이징
			
			
			
		}catch(Exception e) {
			throw new ControllerException(e);
		}
		
		return "community/3-01_community";
		
	}//communityPage
	
	
	//특정 게시글 조회 및 댓글조회
	@GetMapping("/post")
	public String communityPost(CommunityDTO dto, Model model) throws ControllerException{
		log.debug("post({},{})invoked.", dto, model);
	
		
		try {
			this.communityService.updateCommentCount(dto.getFb_number());
			
			CommunityVO board = this.communityService.read(dto);
			
			log.info("\t+board: "+board);
			model.addAttribute("_BOARD_", board);
			
			
//			------------------------------------------------특정게시글
			List<CommentVO2> commentList = commentService2.readComment(board.getFb_number());
			model.addAttribute("_COMMENTLIST_", commentList);
//			------------------------------------------------댓글리스트
			
//			------------------------------------------------게시글 프로필
			//#게시글 프로필
			List<ProfileVO> profileInfo = this.profileService.getProfile(board.getUser_email());
			if(profileInfo.size() == 0) { model.addAttribute("profileResult", "false"); }
			else { model.addAttribute("profileResult", "true"); } //if-else
//			------------------------------------------------댓글 프로필
			//#게시글 프로필
			List<List> profileCList = new ArrayList<List>();
			commentList.forEach(e -> {
				try {
					List<UserProfileVO> profileComment = this.profileService.getUserNaP(e.getUser_email());
					profileCList.add(profileComment);
				} catch (ServiceException e1) { ;; }
			});
			model.addAttribute("profileCList", profileCList);
			
			
			
		}catch(Exception e) {
			throw new ControllerException(e);
		}//try-catch
		
		return "community/3-03_communityPost";
	}//get
	
	
	//특정 게시글 삭제
	@GetMapping("/remove")
	public String remove(CommunityDTO dto, HttpServletRequest req) throws ControllerException {
		
		log.debug("modify({},{})invoked.", dto, req);
		
		int fb_number=Integer.parseInt(req.getParameter("num"));
		dto.setFb_number(fb_number);
		
		try {
			Boolean isResult = this.communityService.remove(dto);
			log.info("{}",  isResult);
			
			return "redirect:/community"; 
		}catch(Exception e) {
			throw new ControllerException(e);
		}//try-catch
		
	}//remove
	
	//게시글 등록
	@PostMapping("/register")
	public String register(@ModelAttribute("reg")CommunityDTO dto, HttpServletRequest req) throws ControllerException{
		
		log.trace("regiser({}) invoked.", dto);
			
		try {
			boolean isResult = this.communityService.create(dto); 
			log.info("{}",  isResult);
	
			
			return "redirect:/community";
		}catch(Exception e) {
			throw new ControllerException(e);
		}//try-catch
		
	}//register
	
		
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(HttpServletRequest req, CommunityDTO dto) throws ControllerException {
		log.trace("modify({}, {}) invoked.", dto,req);

		String fb_number=req.getParameter("fb_number");
		
		try {
			Boolean isResult = this.communityService.update(dto);
			log.info("{}",  isResult);
			
		}catch(Exception e) {
			throw new ControllerException(e);
		}//try-catch
			
			return "redirect:/community/post?fb_number="+fb_number;
		
	} // modify
			
	
	//댓글 등록
	@PostMapping("/commentWrite")
	public String commentWrite(CommentDTO dto, HttpServletRequest req) throws ControllerException{
		log.info("commentWrite() invoked");
			
		try {
			
			int fb_number = Integer.parseInt(req.getParameter("fb_number"));
			dto.setFb_number(fb_number);
			
			boolean isResult = commentService2.writeComment(dto);
			log.info("{}",  isResult);
				
			
			
			return "redirect:/community/post?fb_number="+fb_number;
			
		}catch(Exception e) {
			throw new ControllerException(e);
		}//try-catch
	
		
	}// commentWrite
	
	//댓글 수정
	@PostMapping("/commentUpdate")
	public String commentUpdate(CommentDTO dto) throws ControllerException{
		
		try {
			this.commentService2.updateComment(dto);
			
			return "redirect:/community/post?fb_number="+dto.getFb_number();
		}catch(Exception e) {
			throw new ControllerException(e);
			
		}//try-catch
	}//commentUpdate
	
	//댓글삭제
	@PostMapping("/commentDelete")
	public String commentDelete(CommentDTO dto,String fb_number) throws ControllerException{
		
		try {
			this.commentService2.deleteComment(dto);
			
			return "redirect:/community/post?fb_number="+fb_number;
		}catch(Exception e) {
			throw new ControllerException(e);
			
		}//try-catch
	}//commentUpdate
	
	
	

	
	
	
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	} // communityPost	
	
	
} // end class
