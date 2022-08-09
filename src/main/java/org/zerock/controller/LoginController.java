package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login")	//기본URI(Base URI)
@Controller
public class LoginController {
	
	@RequestMapping
//	@PostMapping("/login")
	public String login() {
		log.trace("login() invoked.");
		
		return "login/1-02_login";
	}
	
	@GetMapping("/selectAccount")
	public String selectAccount() {
		log.trace("selectAccount() invoked.");
		
		return "login/1-03_selectAccount";
	}
	
	@RequestMapping("/signUp_student")
	public String signUp_student() {
		log.trace("signUp_student() invoked.");
		
		return "login/1-04_signUpStudent";
	}
	
	@RequestMapping("/signUp_tutor")
	public String signUp_tutor() {
		log.trace("signUp_tutor() invoked.");
		
		return "login/1-04_signUpTutor";
	}
	
	@RequestMapping("/home")
	public String loginHome() {
		log.trace("loginHome() invoked.");
		
		return "login/1-01_homeLogin";
	}
	
	@RequestMapping("/sendMail")
//	@PostMapping("/sendMail")
	public String sendMail() {
		log.trace("sendMail() invoked.");
		
		return "login/1-05_sendMail";
	}
	
	@GetMapping("/completeSignup")
	public String completeSignup() {
		log.trace("completeSignup() invoked.");
		
		return "login/1-06_completeSignup";
	}
	
	
	@GetMapping("/signupReq")
	public String signupReq() {
		log.trace("signupReq() invoked.");
		
		return "login/1-08_signupReq";
	}
	

}//end class