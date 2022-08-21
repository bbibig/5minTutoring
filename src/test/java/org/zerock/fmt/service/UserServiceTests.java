package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserServiceTests {

	@Setter(onMethod_ = @Autowired)
	private UserService userService;

	
	@Test
	@Order(1)
	@DisplayName("getAllUser 전체회원조회")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void getAllUser() throws ServiceException {
		log.info("getAllUserTest");
		
		List<UserVO> list = this.userService.getAllUser();
		assertNotNull(list);
		list.forEach(log::info);
	}//getAllUserTest
	
	
	@Test
	@Order(2)
	@DisplayName("getUserInfo 회원정보조회")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void getUserInfo() throws ServiceException {
		log.trace("getUserInfo");
		
		String user_email = "STemail_3";
		UserVO vo = this.userService.getUserInfo(user_email);
		assertNotNull(vo);
		log.info("\t + vo : {}", vo );
		
	}//getUserInfo
	
	
	@Test
	@Order(3)
	@DisplayName("singUpStrudent 학생회원가입")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUpStrudent() throws ServiceException {
		log.trace("singUpStrudent");

		UserDTO newStudent = new UserDTO("st@email_1", "1111","nick","name","20020202","여자","01022222222",
											"중학생","1학년",null,null,null,null);
		Boolean Result = this.userService.singUpStrudent(newStudent);
		log.info("\t + Result : {}", Result);
		
	}//singUpStrudent
	
	
	@Test
	@Order(4)
	@DisplayName("singUPTutor 튜터회원가입")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUPTutor() throws ServiceException {
		log.trace("singUPTutor");
		
		UserDTO newTutor = new UserDTO("tt@email_6","1111","nick2","name2","20020202","남자","11111111111",
										null,null,"졸업생","수학","file:name",null);
		Boolean Result = this.userService.singUPTutor(newTutor);
		log.info("\t + Result : {}", Result);
		
	}//singUPTutor
	
	@Test
	@Order(5)
	@DisplayName("updateUser 유저정보수정")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateUser() throws ServiceException {
		log.trace("updateUser");
		
		UserDTO user = new UserDTO();
		user.setUser_email("STemail_2");
		user.setUser_pw("변경11");
		user.setSt_grade("1학년");
		Boolean Result = this.userService.updateUser(user);
		log.info("\t + Result : {}", Result);
		
	}//updateUser
	
	
	@Test
	@Order(6)
	@DisplayName("tutorPass 튜터 승인 확인")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void tutorPass() throws ServiceException {
		log.trace("tutorPass");
		
		String user_email = "TTemail_1";
		Boolean Result = this.userService.tutorPass(user_email);
		log.info("\t + Result : {}", Result);
		
	}//tutorPass
	
	
	@Test
	@Order(7)
	@DisplayName("userStatus 유저 탈퇴->정지")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void userStatus() throws ServiceException {
		log.trace("userStatus");
		
		String user_email = "TTemail_5";
		Boolean Result = this.userService.userStatus(user_email);
		log.info("\t + Result : {}", Result);
		
	}//userStatus
	
	@Test
	@Order(8)
	@DisplayName("updateHandGet 손들기 구매, 획득")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateHandGet() throws ServiceException {
		log.trace("updateHandGet");
		
		Integer h_count = 3;
		String user_email ="TTtest@gmail.com";
		boolean Result = this.userService.updateHandGet(h_count, user_email);
		log.info("\t + Result : {}", Result);
		
	}//updateHandGet
	
	
	@Test
	@Order(9)
	@DisplayName("updateHandUse 손들기 사용")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateHandUse() throws ServiceException {
		log.trace("updateHandUse");
		
		Integer h_count = 3;
		String user_email = "학생테스트";
		Boolean Result = this.userService.updateHandUse(h_count, user_email);
		log.info("\t + Result : {}", Result);
		
	}//updateHandUse
}//end class
