package org.zerock.fmt.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.TutorService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/tutor")
@Controller
public class TutorController {
	
	private TutorService tutorService;
	
	@GetMapping("/main")
	public String tpMain(Model model, HttpServletRequest req) throws ControllerException {
		log.trace("2-01_tpMain <<< 튜터페이지 메인");
		
		try {
			// 신규 튜터
			List<TutorPageVO> recentList = this.tutorService.getRecentTCard();
			recentList.forEach(log::trace);
			model.addAttribute("_RECENT_LIST_", recentList);
			
			// 추천 튜터
			String subject = "국어";
			String searchType = "누적답변순";
			
			if(req.getParameter("subject") != null) { subject = req.getParameter("subject"); }
			if(req.getParameter("searchType") != null) { searchType = req.getParameter("searchType"); }
			
			List<TutorPageVO> tutorVO = this.tutorService.getSortedTCard(subject, searchType);
			tutorVO.forEach(log::trace);
			model.addAttribute("_SORTED_TUTOR_", tutorVO);
			
		} catch (Exception e) { throw new ControllerException(e); }

		return "tutor/2-01_tpMain";
	} // tpMain
	

	@GetMapping("/info")
	public String tutorInfo(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-02_tutorpage_info <<< 튜터정보 페이지");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-02_tutorpage_info";
	}// tutorInfo
	
	
	@GetMapping("/writeReview")
	public String writeReview(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-03_writereview <<< 학생리뷰 작성 페이지");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-03_writereview";
	} // writeReview
	
	@GetMapping("/reviewList")
	public String reviewList(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-04_reviewlist <<< 학생리뷰 목록 페이지");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-04_reviewlist";
	} // reviewList
	
	
	@GetMapping("/ask")
	public String ask(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-05_ask <<< 튜터에게 질문하기");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-05_ask";
	} // ask
	
	
	@GetMapping("/writeAnswer")
	public String writeAnswer() {
		log.trace("2-06_writeAnswer <<< 답변 작성하기 - 튜터");
		
		return "tutor/2-06_writeAnswer";
	} // writeAnswer
	
	
	@GetMapping("/watchAnswer")
	public String watchAnswer() {
		log.trace("2-07_watchAnswer <<< 답변 보기 - 학생, 튜터");
		
		return "tutor/2-07_watchAnswer";
	} // watchAnswer
	
	
	@GetMapping("/tutoring")
	public String tutoring(Model model, HttpServletRequest req) throws ControllerException {
		log.trace("2-08_tutoring <<< 튜터에게 과외받기");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-08_tutoring";
	} // tutoring
	
	
	@GetMapping("/tutoringAsk")
	public String tutoringAsk() {
		log.trace("2-09_tutoringAsk <<< 과외하기 질문");
		
		return "tutor/2-09_tutoringAsk";
	} // tutoringAsk

} // end class
