package org.zerock.fmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Controller
@RequestMapping("/naver")
public class NaverController {

	@RequestMapping("/userNaverLoginPro")
	public void naverLogin() {
		
	}//Naverlogin
	
}//end class
