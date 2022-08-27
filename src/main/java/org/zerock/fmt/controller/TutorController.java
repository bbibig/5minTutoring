package org.zerock.fmt.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.service.TutorService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/tutor")
@Controller
public class TutorController implements InitializingBean {
	
	private TutorService tutorService;
	
	@GetMapping("/tutorMain")
	public String tpMain(Model model) throws ControllerException {
		log.trace("2-01_tpMain <<< 튜터페이지 메인");
		
		try {
			List<TutorPageVO> recentList = this.tutorService.getRecentTCard();
			recentList.forEach(log::trace);
			
			model.addAttribute("_RECENT_LIST_", recentList);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
		return "tutor/2-01_tpMain";
	} // tpMain
	

	@GetMapping("/info")
	public String tutorinfo(Model model) throws ControllerException {
		log.trace("2-02_tutorpage_info <<< 튜터정보 페이지");
		
		try {
			// Session에서 아이디 얻어오기? select를 id로 해야 할듯..
			
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(21);
			log.info("tutorInfo: {}", tutorInfo);
			
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
		return "tutor/2-02_tutorpage_info";
	}//tutor info
	
	
	@GetMapping("/writeReview")
	public String writeReview() {
		log.trace("writeReview()invoked");
		
		return "tutor/2-03_tutorpage_writereview";
	}//tutor writeReview
	
	@GetMapping("/reviewList")
	public String reviewList() {
		log.trace("reviewList()invoked");
		
		return "tutor/2-04_tutorpage_reviewlist";
	}//tutor reviewList
	
	
	@GetMapping("/ask")
	public String ask() {
		log.trace("2-05_ask <<< 튜터에게 질문하기");
		
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
	public String tutoring() {
		log.trace("tutoring()invoked");
		
		return "tutor/2-08_tutorpage_tutoring";
	}//tutor tutoring
	
	
	@GetMapping("/tutoringAsk")
	public String tutoringAsk() {
		log.trace("2-09_tutoringAsk <<< 과외하기 질문");
		
		return "tutor/2-09_tutoringAsk";
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("의존성 주입 완료");
		
		Objects.requireNonNull(this.tutorService);
		log.trace("\t + this.tutorService: {}", tutorService);
		
	} // afterPropertiesSet
	
	
	
} // end class
