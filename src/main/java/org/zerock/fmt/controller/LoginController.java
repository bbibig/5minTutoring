package org.zerock.fmt.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login")	
@Controller
public class LoginController{
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;	//--- UserService

	@Setter(onMethod_ = @Autowired)
	private FileLoad fileupload;		//---- File관련 
	
	@Setter(onMethod_ = @Autowired)
	private FileService fileservice;	//---- FileService
//------------------------------------------------------------//
	
	//로그인화면
	@GetMapping
	public String login() {
		log.trace("login() invoked.");
		return "login/1-02_login";
	}
	
	//회원가입 선택 
	@GetMapping("/selectAccount")
	public String selectAccount() {
		log.trace("selectAccount() invoked.");
		return "login/1-03_selectAccount";
	}
	
	//회원가입폼 - 학생
	@GetMapping("/signUp_student")		
	public String signUp_student() {
		log.trace("학생 회원가입 폼");	
		return "login/1-04_signUpStudent";
	}//signUp_student---1
	
	//학생회원가입 
	@PostMapping("/signUp_student")
	public String signUpStudent(UserDTO DTO) throws ServiceException {
	
		try {
			if(this.userService.singUpStrudent(DTO)) { 
				log.info("학생 회원가입 성공");
			} else log.info("회원가입실패");
			return "redirect:/login";
		} catch(Exception e) { throw new ServiceException(e); }
	}//signUpStudent---2
	
	
	//회원가입폼 - 튜터
	@GetMapping("/signUp_tutor")		
	public String signUpTutor() {
		log.trace("signUp_tutor() invoked.");
		return "login/1-04_signUpTutor";
	}//signUp_tutor---1
	
	//튜터회원가입
	@PostMapping(path="/signUp_tutor")
	public String signUpTutor(UserDTO DTO, List<MultipartFile> file) throws ControllerException {
		log.trace("signUpTutor() invoked.");

		try {
			
			boolean UserResult = this.userService.singUPTutor(DTO);
			log.info("\t + 유저 회원가입 : {}", UserResult);
			
			//-------------------------------------------------
			
			List<FileDTO> fileDTO = this.fileupload.uploadFile(file, DTO.getUser_email());
			log.info("\t + fileDTO : {}", fileDTO);
			
			//--------------------------------------------------
			
			for(FileDTO filedto : fileDTO) {
				int fileResult = this.fileservice.createFiles(filedto);
				log.info("\t + File Mapper insert : {}", fileResult);
			}//for 
				log.info("튜터 회원가입 성공");
				
			return "redirect:/";
		} catch (Exception e) { throw new ControllerException(e); }
	}// signUpStudent---2
	
	@PostMapping("/nick")
	@ResponseBody
	public String nickCheckPost(String newNick) throws ControllerException{
		log.trace("nickCheckPost : 닉네임중복체크");
		try{ 
			int result = this.userService.getNicCheck(newNick);
			log.info("\t + result : {} ", result);
			if(result != 0) {
				return "fail";
			} else {
				return "success";
			}
		} catch( Exception e) { throw new ControllerException(e); }
		
	}//nickCheckPost
	
	//로그인 후 메인화면
	@RequestMapping("/home")
	public String loginHome() {
		log.trace("loginHome() invoked.");
		
		return "login/1-01_homeLogin";
	}
	
	// 튜터 회원가입 요청
	@GetMapping("/signupReq")
	public String signupReq() {
		log.trace("signupReq() invoked.");
		
		return "login/1-08_signupReq";
	}
	

	// 이메일 찾기
	@GetMapping("/findMyEmail")
	public String findMyEmail() {
		log.trace("findMyEmail() invoked.");
		
		return "login/1-09_findMyEmail";
	}
	
	// 이메일 찾기 - 성공
	@GetMapping("/foundEmail")
	public String foundEmail() {
		log.trace("signupReq() invoked.");
		
		return "login/1-10_foundEmail";
	}
	
	// 이메일 찾기 - 실패
	@GetMapping("/notFoundEmail")
	public String notFoundEmail() {
		log.trace("notFoundEmail() invoked.");
		
		return "login/1-11_notFoundEmail";
	}
	
	// 비밀번호 찾기
	@GetMapping("/findMyPassword")
	public String findMyPassword() {
		log.trace("findMyPassword() invoked.");
		
		return "login/1-12_findMyPassword";
	}

	@PostMapping("findMyPassword")
	public void findMyPassword(String user_email) {
	
		
	}
	

}//end class