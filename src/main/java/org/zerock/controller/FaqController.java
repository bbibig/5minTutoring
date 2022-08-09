package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/faq")	//기본URI(Base URI) 부모폴더 이름
@Controller
public class FaqController {

	@RequestMapping("/faqList")		// 각자 정하기
	public String faq() {
		log.trace("5_faq 자주 묻는 질문");
		
		return "faq/5_FAQ";
	} // String faq
	
} // end class
