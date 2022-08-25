package org.zerock.fmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.mapper.FileMapperTest;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
//@Controller
//@RequestMapping("/login")
public class FileController {

	@Setter(onMethod_ = @Autowired)
	private UserService userService;

	@Setter(onMethod_ = @Autowired)
	private FileLoad fileupload;
	
	@Setter(onMethod_ = @Autowired)
	private FileMapperTest filemapper;

	// 튜터회원가입
//	@PostMapping(path="/signUp_tutor")
	public String signUpTutor(UserDTO DTO, List<MultipartFile> file) {
		log.trace("튜터회원가입... 첨부파일 미치겠음..");

		try {
			
			boolean UserResult = this.userService.singUPTutor(DTO);
			log.info("\t + 유저 회원가입 : {}", UserResult);
			
			//-------------------------------------------------
			
			List<FileDTO> fileDTO = this.fileupload.uploadFile(file, DTO.getUser_email());
			log.info("\t + fileDTO : {}", fileDTO);
			
			//--------------------------------------------------
			for(FileDTO filedto : fileDTO) {
				int fileResult = this.filemapper.insertFile(filedto);
				log.info("\t + File Mapper insert : {}", fileResult);
			}//for 
			
			if (UserResult) {
				log.info("튜터 회원가입 성공");
			} else
				log.info("회원가입실패");

			return "/login/signUp_tutor";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}// signUpStudent---2
		// 회원가입폼 - 튜터

//	@GetMapping("/signUp_tutor")
	public String signUpTutor() {
		log.trace("signUp_tutor() invoked.");
		return "login/1-04_signUpTutor";
	}// signUp_tutor---1

}// end class
