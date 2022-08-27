package org.zerock.fmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.MypageService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage")	//기본URI(Base URI)
@Controller
public class MypageController {
	
	@Setter(onMethod_= @Autowired)
	private UserService userService;
	
	@Setter(onMethod_= @Autowired)
	private MypageService mypageService;
	
//============================================================	
	
	@GetMapping("/studentPage")
	public String studentPage(String user_email, Model model) throws ControllerException{
		log.trace("마이페이지 기본정보 조회(학생)");
		
		try {
			UserVO vo = this.userService.getUserInfo(user_email);
			//유저 정보는 세션에 올린 정보를 가져와야할것임(일단 requestscope에 올린 정보로 가져옴)
			model.addAttribute("_USERINFO_", vo);
			
			return "mypage/7-01_StudentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보조회(학생)
	
	@RequestMapping("/studentPageModify")
	public String studentPage(UserDTO dto, RedirectAttributes rttrs) throws ControllerException{
		log.trace("마이페이지 기본정보 수정(학생)");
		
		try {
			if(this.userService.updateUser(dto)) {
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 성공");
			} else {
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 오류");
			}//if-else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

		return "redirect:/mypage/studentPage?user_email=" + dto.getUser_email();
	}// 기본정보 수정(학생)
	
	
	@GetMapping("/tutorPage")
	public String tutorPage(String user_email, Model model) throws ControllerException{
		log.trace("마이페이지 기본정보 조회(튜터)");
		
		try {
			UserVO vo = this.userService.getUserInfo(user_email);
			//유저 정보는 세션에 올린 정보를 가져와야할것임(일단 requestscope에 올린 정보로 가져옴)
			model.addAttribute("_USERINFO_", vo);
			
			return "mypage/7-02_TutorPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// 기본정보 조회(튜터)
	
	@RequestMapping("/tutorPageModify")
	public String tutorPageModify(UserDTO dto, RedirectAttributes rttrs) throws ControllerException{
		log.trace("마이페이지 기본정보 수정(튜터)");
		
		try {
			if(this.userService.updateUser(dto)) {
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 성공");
			} else {
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 오류");
			}//if-else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

		return "redirect:/mypage/tutorPage?user_email=" + dto.getUser_email();
	}// 기본정보 수정(튜터)
	
	
	@GetMapping("/myQuestion")			//GET	
	public String myQuestion() {
		log.trace("7-03_MyQuestionList");
		
		return "mypage/7-03_MyQuestionList";
	}// myQuestion
	
	@GetMapping("/community/write")		//GET	
	public String communityWrite() {
		log.trace("7-04_CommunityW");
		
		return "mypage/7-04_CommunityW";
	}// communityWrite
	
	@GetMapping("/community/comments")		//GET	
	public String communityComments() {
		log.trace("7-05_CommunityC");
		
		return "mypage/7-05_CommunityC";
	}// communityComments
	
	@GetMapping("/qList")	// GET
	public String qList() {
		log.trace("7-06_QList");
		
		return "mypage/7-06_QList";
	}// qList
	
	@GetMapping("/question") // GET
	public String question() {
		log.trace("7-07_Q");
		
		return "mypage/7-07_Q";
	}// question
	
	@GetMapping("/qAndA")	// GET
	public String qAndA() {
		log.trace("7-07_QandA");
		
		return "mypage/7-07_QandA";
	}// qAndA
	
	@RequestMapping("/unregister")	// POST
	public String unregister() {
		log.trace("7-08_Unregister");
		
		return "mypage/7-08_Unregister";
	}// unregister
	
	@GetMapping("/unregister/completed")	// GET
	public String unregisterCompleted() {
		log.trace("7-09_UnregisterCompleted");
		
		return "mypage/7-09_UnregisterCompleted";
	}// unregisterCompleted
	
	@GetMapping("/studentHands/use")		//Get
	public String studentHandsUse() {
		log.trace("7-10_StudentHandsListUse");
		
		return "mypage/7-10_StudentHandsListUse";
	}// studentHandsUse
	
	@GetMapping("/studentHands/buy")		//GET
	public String studentHandsBuy() {
		log.trace("7-11_StudentHandsListBuy");
		
		return "mypage/7-11_StudentHandsListBuy";
	}// studentHandsBuy
	
	@GetMapping("/studentHands/buy/detail")		//GET
	public String studentHandsBuyDetail() {
		log.trace("7-12_StudentHandsListBuyD");
		
		return "mypage/7-12_StudentHandsListBuyD";
	}// studentHandsBuyDetail
	
	@GetMapping("/tutorHands/get")		//GET
	public String tutorHandsGet() {
		log.trace("7-13_TutorHandsListGet");
		
		return "mypage/7-13_TutorHandsListGet";
	}// tutorHandsGet
	
	@GetMapping("/tutorHands/withdraw")		//GET
	public String tutorHandsWithdraw() {
		log.trace("7-14_TutorHandsListWithdraw");
		
		return "mypage/7-14_TutorHandsListWithdraw";
	}// tutorHandsWithdraw
	
	@RequestMapping("/withdraw")	// POST
	public String withdraw() {
		log.trace("7-15_Withdraw");
		
		return "mypage/7-15_Withdraw";
	}// withdraw
	
	@GetMapping("/withdraw/completed")	// GET
	public String withdrawCompleted() {
		log.trace("7-16_WithdrawCompleted");
		
		return "mypage/7-16_WithdrawCompleted";
	}// withdrawCompleted

}//end class