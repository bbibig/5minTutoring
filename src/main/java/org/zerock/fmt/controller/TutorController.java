package org.zerock.fmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/tutor")
@Controller
public class TutorController {
	
	@GetMapping("/tutorMain")
	public String tpMain() {
		log.trace("2-01_tpMain <<< 튜터페이지 메인");
		
		return "tutor/2-01_tpMain";
	} // tpMain
	
	
	@RequestMapping("/info")
	public String tutorinfo() {
		log.trace("tutorinfo()invoked");
		
		return "tutor/2-02_tutorpage_info";
	}//tutor info
	
	@RequestMapping("/writeReview")
	public String writeReview() {
		log.trace("writeReview()invoked");
		
		return "tutor/2-03_tutorpage_writereview";
	}//tutor writeReview
	
	@RequestMapping("/reviewList")
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
	
	
	@RequestMapping("/tutoring")
	public String tutoring() {
		log.trace("tutoring()invoked");
		
		return "tutor/2-08_tutorpage_tutoring";
	}//tutor tutoring
	
	
	@GetMapping("/tutoringAsk")
	public String tutoringAsk() {
		log.trace("2-09_tutoringAsk <<< 과외하기 질문");
		
		return "tutor/2-09_tutoringAsk";
	} // tutoringAsk
	
	
	
} // end class
