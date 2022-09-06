package org.zerock.fmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.common.SharedScopeKeys;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

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
	
	//---------------------------------------로그인 
	@GetMapping
	public String login() {
		log.trace("login() invoked.");
		return "login/1-02_login";
	}
	@PostMapping("/Loginpost")
	public String login(String user_email, String user_pw, Model model, RedirectAttributes rttrs)
														throws ControllerException {
		log.trace("loginPost Invoked.");
		try{
			UserVO vo = this.userService.gettLoginUser(user_email, user_pw);
			log.info("\t + post vo : {}", vo);
			model.addAttribute(SharedScopeKeys.LOGIN_USER,vo);
				if(vo==null) {
					rttrs.addFlashAttribute("_LOGIN_", "로그인에 실패하였습니다.");
					return "redirect:/login";
				}//if
			return "login/Loginpost";
		}catch (Exception e) { throw new ControllerException(e); }//try-catch
	}//login
	
	//로그인 후 메인화면
	@RequestMapping("/home")
	public String loginHome() {
		log.trace("loginHome() invoked.");
		return "login/1-01_homeLogin";
	}
	
	//회원가입 선택 
	@GetMapping("/selectAccount")
	public String selectAccount() {
		log.trace("selectAccount() invoked.");
		return "login/1-03_selectAccount";
	}
	
	//---------------------------------------회원가입(학생)
	@GetMapping("/signUp_student")		
	public String signUp_student() {
		log.trace("학생 회원가입 폼");	
		return "login/1-04_signUpStudent";
	}//signUp_student---1
	
	//학생회원가입 
	@PostMapping("/signUp_student")
	public String signUpStudent(UserDTO DTO) throws ControllerException {
		try {
			if(this.userService.singUpStrudent(DTO)) { 
				log.info("학생 회원가입 성공");
			} else log.info("회원가입실패");
			return "redirect:/login";
		} catch(Exception e) { throw new ControllerException(e); }
	}//signUpStudent---2
	
	
	//---------------------------------------회원가입(튜터)
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
				
			List<FileDTO> fileDTO = this.fileupload.uploadFile(file, DTO.getUser_email());
			log.info("\t + fileDTO : {}", fileDTO);
						
			for(FileDTO filedto : fileDTO) {
				int fileResult = this.fileservice.createFiles(filedto);
				log.info("\t + File Mapper insert : {}", fileResult);
			}//for 
				log.info("튜터 회원가입 성공");
			return "redirect:/";
		} catch (Exception e) { throw new ControllerException(e); }
	}// signUpStudent---2
	
	//---------------------------------------닉네임 중복확인
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
	
	//---------------------------------------비밀번호 찾기
	@GetMapping("/findMyPassword")
	public String findMyPassword() {
		log.trace("findMyPassword() invoked.");
		return "login/1-12_findMyPassword";
	}
	@PostMapping(path="/findMyPassword")
	public ResponseEntity<String> findMyPassword(UserDTO dto) throws ControllerException {
		log.trace("findMyPassword invoked.");
		
		try{ 
			String result = this.userService.findPassword(dto);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "text/html; charset=utf-8");
			return new ResponseEntity<String>(result, header,HttpStatus.OK);
		} catch ( Exception e) { throw new ControllerException(e); }
	}//findMyPassword

//	------------------------------------------ 로그아웃 
	
	@GetMapping("/logout")
	public void logout(RedirectAttributes rttrs)throws ControllerException{
		log.trace("logOut() invoked.");
	}//logout

	// 튜터 회원가입 요청
	@GetMapping("/signupReq")
	public String signupReq() {
		log.trace("signupReq() invoked.");
		
		return "login/1-08_signupReq";
	}

//	------------------------------------------ 본인인증문자
	@ResponseBody
	@GetMapping("/phoneCheck")
	public String sendSMS(@RequestParam("user_phone")String user_phone) { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

		this.userService.certifiedPhoneNumber(user_phone,randomNumber);
		
		return Integer.toString(randomNumber);
	}
}//end class