package org.zerock.fmt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.fmt.domain.UserDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Component
public class ProfileLoad {
	
	public void uploadProfile(MultipartFile file, UserDTO user) {
		
		//파일 경로
		String uploadFolder = "C://temp/profile/";
		File uploadPath = new File(uploadFolder);
		if(!uploadPath.exists()) {uploadPath.mkdirs();} // if
		
		//파일 이름: 유저 닉네임_profile
		String profileName = user.getUser_nick() + "_profile.png";
		
		// 프로필 파일 저장
		File saveFile = new File(uploadPath, profileName);
		try {
			file.transferTo(saveFile);
			log.info("\t+ {}님의 프로필 파일 저장 완료", user.getUser_email());
		} catch (IllegalStateException | IOException e) { e.printStackTrace(); }
		
	}//uploadProfile()

}// end class




























