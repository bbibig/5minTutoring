package org.zerock.fmt.mapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserProfileVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ProfileMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private ProfileMapper mapper;
	
	
	//1. 프로필 사진 정보 조회
	@Test
	@Order(1)
	@DisplayName("1. selectProfile")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectProfile() throws DAOException {
		log.trace("프로필 사진 정보 조회");
		
		ProfileDTO dto = new ProfileDTO();
		dto.setUser_email("test@gmail.com");
		
		List<ProfileVO> list = this.mapper.selectProfile(dto.getUser_email());
		list.forEach(e -> log.info(e));
		
	}//selectProfile()
	
	//2. 프로필 사진 등록
	@Test
	@Order(2)
	@DisplayName("2. insertProfile")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void insertProfile() throws DAOException {
		
		List<ProfileVO> list = this.mapper.selectProfile("test@gmail.com");
		
		if(list.size() == 0) {
			ProfileDTO dto = new ProfileDTO();
			dto.setUser_email("test@gmail.com");
			dto.setFile_name("crystal003_profile.png");
			
			this.mapper.insertProfile(dto);
			log.trace("프로필 사진 등록 성공");
		} else {
			log.info("프로필 사진 이미 존재함");
		}// if-else
		
	}//insertProfile()
	
	//3. 프로필 사진 수정
	@Test
	@Order(3)
	@DisplayName("3. updateProfile")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void updateProfile() throws DAOException {
		
		List<ProfileVO> list = this.mapper.selectProfile("test@gmail.com");
		
		if(list.size() != 0) {
			ProfileDTO dto = new ProfileDTO();
			dto.setUser_email("test@gmail.com");
			
			this.mapper.updateProfile(dto);
			log.info("프로필 사진 수정 완료");
		} else {
			log.info("프로필 사진 등록 필요");
		}// if-else
		
	}//insertProfile()
	
	//4. 프로필 사진 정보 조회 (닉네임)
	@Test
	@Order(4)
	@DisplayName("4. selectUserNick")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectUserNick() throws DAOException {
		log.trace("유저 닉네임 조회");
		
		String userNick = this.mapper.selectUserNick("seosujung0@gmail.com");
		log.info("\t+ 유저닉네임: {}", userNick);
		
	}//selectProfile()
	
	//5. 튜터 닉네임 조회
	@Test
	@Order(5)
	@DisplayName("5. selectTutorNick")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectTutorNick() throws DAOException {
		String userNick = this.mapper.selectTutorNick(65);
		log.info("\t+ 튜터닉네임: {}", userNick);
		
	}//selectTutorNick()
	
	//6. 튜터 이메일 조회
	@Test
	@Order(6)
	@DisplayName("6. selectTutorEmail")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectTutorEmail() throws DAOException {
		String userEmail = this.mapper.selectTutorEmail(65);
		log.info("\t+ 튜터이메일: {}", userEmail);
		
	}//selectTutorEmail()
	
	//7. 유저 닉네임, 사진정보 조회
	@Test
	@Order(7)
	@DisplayName("7. 유저 닉네임, 사진정보 조회")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectUserNaP() throws DAOException {
		List<UserProfileVO> list = this.mapper.selectUserNaP("sdfdhh45899@gmail.com");
		list.forEach(e -> log.info(">>>>{}", e));
		
	}//유저 닉네임, 사진정보 조회()
	
}// end class






















