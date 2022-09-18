package org.zerock.fmt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.service.InquiryQuestionService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/inquiry")	 // post방식
@Controller
public class InquiryController {
	
	@Setter(onMethod_= @Autowired)
	private InquiryQuestionService inquiryService;
	
	@Setter(onMethod_ = @Autowired)
	private FileLoad fileupload;		//---- File관련 
	
	@Setter(onMethod_ = @Autowired)
	private FileService fileservice;	//---- FileService
	
//	============================================================	
	
	@GetMapping
	public String inquiry()  {
		log.trace("inquiry() invoked.");
		return "inquiry/6_Inquiry";
	
	} // inquiry (문의하기 폼)
	
	
	@PostMapping("/inquiryCreate")
	public String inquiryCreate(CriteriaMyPage cri, Model model, InquiryQuestionDTO dto, HttpServletRequest req, RedirectAttributes rttrs ) throws ControllerException {
		log.trace("1:1 문의하기");
		
		HttpSession session = req.getSession();
		
		UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
		String user_email = userVO.getUser_email();
		log.info("user_email: {}", user_email);
		
		dto.setUser_email(user_email);
		
		try {
			if(this.inquiryService.createIQ(dto)) { 
				rttrs.addFlashAttribute("_INQUIRYRESULT_", "문의하기 생성 성공"); 
			} else { 
				rttrs.addFlashAttribute("_INQUIRYRESULT_", "문의하기 생성 오류");
			} //if - else
			
		} catch (ServiceException e) { 
			throw new ControllerException(e); 
		}// try-catch
		
		return "redirect:/mypage/qList?currPage="+ cri.getCurrPage();

	} // inquiryCreate (문의 작성)
	

} // end class
