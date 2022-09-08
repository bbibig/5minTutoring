package org.zerock.fmt.service;

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
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ProfileServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private ProfileService service;
	
	
	//1. 프로필 사진 정보 조회
	@Test
	@Order(1)
	@DisplayName("1. 프로필 사진 조회")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getProfile() throws DAOException, ServiceException {
		log.info("프로필 사진 정보 조회");
		
		ProfileDTO dto = new ProfileDTO();
		dto.setUser_email("test@gmail.com");
		
		List<ProfileVO> list = this.service.getProfile(dto.getUser_email());
		
		list.forEach(e -> log.info(e));
		
	}//getProfile()
	
	//2. 프로필 사진 등록
	@Test
	@Order(2)
	@DisplayName("2. 프로필 사진 등록")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void createProfile() throws DAOException, ServiceException {
		ProfileDTO dto = new ProfileDTO();
		dto.setUser_email("test@gmail.com");
		dto.setFile_name("crystal003_profile.png");
		
		List<ProfileVO> list = this.service.getProfile(dto.getUser_email());
		
		if(list.size() == 0) {
			log.info("프로필 사진 등록");
			this.service.createProfile(dto);
		} else {
			log.info("프로필 사진 이미 존재함");
		}// if-else
		
	}//createProfile()
	
	//3. 프로필 사진 수정
	@Test
	@Order(3)
	@DisplayName("3. 프로필 사진 수정")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void modifyProfile() throws DAOException, ServiceException {
		
		ProfileDTO dto = new ProfileDTO();
		dto.setUser_email("test@gmail.com");
		List<ProfileVO> list = this.service.getProfile(dto.getUser_email());
		
		if(list.size() != 0) {
			this.service.modifyProfile(dto);
			log.info("프로필 사진 수정 완료");
		} else {
			log.info("프로필 사진 등록 필요");
		}// if-else
		
	}//modifyProfile()
	
}// end class






















