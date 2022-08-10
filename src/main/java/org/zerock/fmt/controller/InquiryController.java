package org.zerock.fmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/inquiry")	 // post방식
@Controller
public class InquiryController {
	
	@RequestMapping
	public String inquiry() {
		log.trace("6_inquiry 1:1문의하기");
		
		return "inquiry/6_Inquiry";
	} // String inquiry
	

}
