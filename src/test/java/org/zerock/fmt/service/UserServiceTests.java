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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.UserMapper;

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

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Test
	@Order(1)
	@DisplayName("getAllUser 전체회원조회")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void getStudent() throws ServiceException {
		log.info("getAllUserTest");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setPagesPerPage(5);
		List<UserVO> list = this.userService.getStudent(cri);
		assertNotNull(list);
		list.forEach(log::info);
	}//getAllUser
	
	@Test
	@DisplayName("getTutor")
	void getTutor() throws ServiceException {
		log.info("getAllUserTest");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setPagesPerPage(5);
		List<UserVO> list = this.userService.getTutor(cri);
		assertNotNull(list);
		list.forEach(log::info);
	}//getTutor
	
	@Test
	@DisplayName("getStopUser")
	void getStopUser() throws ServiceException {
		log.info("getAllUserTest");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setPagesPerPage(5);
		List<UserVO> list = this.userService.getStopUser(cri);
		assertNotNull(list);
		list.forEach(log::info);
	}//getStopUser
		
	@Test
	@DisplayName("userCount")
	void userCount() throws DAOException {
		int result = this.userMapper.userCount("Tutor", null);
		log.info("\t + result : {}", result);
	}//userCount
	
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
											"중학생","1학년",null,null,null);
		Boolean Result = this.userService.singUpStrudent(newStudent);
		log.info("\t + Result : {}", Result);
		
	}//singUpStrudent
	
	@Test
	@Order(4)
	@DisplayName("singUpStrudent 학생회원가입")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUpStrudentWithBCryptPassword() throws ServiceException {
		log.trace("singUpStrudentWithBCryptPassword");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@email.net");
		dto.setUser_pw("bcpassword");
		dto.setUser_nick("nickname11");
		dto.setUser_name("김한글");
		dto.setUser_birth("20220831");
		dto.setUser_gender("남자");
		dto.setUser_phone("0100000000");
		dto.setSt_school("중학생");
		dto.setSt_grade("1학년");
		
		String originpw = dto.getUser_pw();
		String bcriptpw = encoder.encode(dto.getUser_pw());
		log.info("\t + originpw : {}", originpw);
		log.info("\t + bcryptpw : {}", bcriptpw);
		dto.setUser_pw(bcriptpw);
		
		boolean result = this.userService.singUpStrudent(dto);
		log.info("\t + result : {}", result );
	}//singUpStrudent
	
	
	@Test
	@Order(4)
	@DisplayName("singUPTutor 튜터회원가입")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUPTutor() throws ServiceException {
		log.trace("singUPTutor");
		
		UserDTO newTutor = new UserDTO("tt@email_6","1111","nick2","name2","20020202","남자","11111111111",
										null,null,"졸업생","수학","file:name");
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
	
	@Test
	@Order(10)
	@DisplayName("selectNicCheck 닉네임중복검사")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testselectNicCheck() throws ServiceException {
		log.trace("selectNicCheck");
		
		String nickName1 = "나는학생";
		String nickName2 = "";
		int resutl1 = this.userService.getNicCheck(nickName1);
		int result2 = this.userService.getNicCheck(nickName2);
		
		log.info("\t + result1 : {}, result2: {}", resutl1, result2);
	}//selectNicCheck
	
	@Test
	@Order(11)
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	@DisplayName("gettLoginUser 로그인하기")
	void gettLoginUser() throws ServiceException {
		log.trace("selectLogin() invoked.");
		
		String user_email="test@email.net";
		String user_pw = "bcpassword";
		UserVO vo = this.userService.loginEmail(user_email);
		log.info("\t+ vo : {}", vo );
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if( encoder.matches(user_pw, vo.getUser_pw()) ){	//암호화비밀번호랑 같으면
			log.info(" 로그인 성공 " );
		} else { log.info("로그인실패"); }
		
	}//selectLogin
	
	@Test
	@DisplayName("loginEmail 로그인정보 가져오기")
	void testloginEmail() throws ServiceException {
		log.trace("loginEmail 로그인 유저 확인");
		String user_email="test@email.net";
		UserVO vo = this.userService.loginEmail(user_email);
		log.info("\t+ vo : {}", vo);
	}//loginEmail
	
	@Test
	@DisplayName("findPassword 비밀번호변경 ")
	void findPassword() throws ServiceException, DAOException  {
		log.trace("findPassword 비밀번호 랜덤 변경");
		UserDTO dto = new UserDTO();
		dto.setUser_email("tutor2@gmail.com");
		
		UserVO vo = this.userService.loginEmail(dto.getUser_email());

		if( vo==null ) {
			//등록되지 않은 아이디 입니다. 
		} else {
			String newPw = "";
			for(int i=0; i<12; i++) {
				newPw += (char) ((Math.random() * 26) + 97);
			}//랜덤비밀번호
			
			dto.setUser_pw(newPw);
			int result = this.userMapper.updatePW(dto);
			log.info("\t + 비밀번호 변경 result : {}", result);
			//이메일발송
			//임시비밀번호가 이메일로 발송되었습니다. 
		}//if-else
	}//findPassword
	
	@Test
	@DisplayName("getWaitTutor 승인 대기 튜터 리스트")
	void getWaitTutor() throws DAOException {
		log.trace("getWaitTutor 승인 대기 튜터 리스트");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(10);
		cri.setCurrPage(2);
		List<UserVO> list = this.userMapper.selectWaitTutor(cri);
		list.forEach(log::info);
	}//getWaitTutor
	
	@Test
	@DisplayName("waitTutorCount 승인 대기 튜터 회원수")
	void waitTutorCount() throws DAOException {
		log.trace("waitTutorCount 승인 대기 튜터 회원수");
		int result = this.userMapper.waitTutorCount();
		log.info("\t + result : {}", result);
		
	}//waitTutorCount
	
	@Test
	@DisplayName("certifiedPhoneNumber")
	void certifiedPhoneNumber() {
		log.trace("certifiedPhoneNumber 휴대폰인증문자보내기");
		this.userService.certifiedPhoneNumber("01089814304", 1234);
		log.info("\t+........");
	}//certifiedPhoneNumber
}//end class
