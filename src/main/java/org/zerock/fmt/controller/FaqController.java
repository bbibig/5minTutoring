package org.zerock.fmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.FaqService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/faq")	
@Controller
public class FaqController {
	
	@Setter(onMethod_= {@Autowired})
	private FaqService faqService;

	
	@GetMapping	
	public String faq(CriteriaMyPage cri, Model model) throws ControllerException {
		log.trace("5_faq 자주 묻는 질문 목록 조회 (사용자)");
		
		try {
			List<FaqVO> list = this.faqService.getFaqList(cri);
			model.addAttribute("_FAQLIST_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.faqService.getFaqTotal());
			model.addAttribute("_FAQPAGENATION_", pageDto);
			
			return "faq/5_FAQ";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
	
	} // String faq
	
} // end class
