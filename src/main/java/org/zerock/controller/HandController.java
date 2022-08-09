package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/hand")	//기본URI(Base URI)
@Controller
public class HandController {
	
	@GetMapping("/buyHands")
	public String buyHands() {
		log.trace("buyHands() invoked.");
		
		return "hand/4-01_buyHands";
	}
	
	@PostMapping("/payPage")
	public String payPage() {
		log.trace("payPage() invoked.");
		
		return "hand/4-02_payPage";
	}

}//end class