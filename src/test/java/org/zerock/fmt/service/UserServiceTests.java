package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
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
	@DisplayName("getStudent ํ์์กฐํ")
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
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(1);
		cri.setKeyword("test");
		cri.setType("E");
		cri.setUser_group("Student");
		int result = this.userMapper.userCount(cri);
		log.info("\t + result : {}", result);
	}//userCount
	
	@Test
	@Order(2)
	@DisplayName("getUserInfo ํ์์?๋ณด์กฐํ")
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
	@DisplayName("singUpStrudent ํ์ํ์๊ฐ์")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUpStrudent() throws ServiceException {
		log.trace("singUpStrudent");

		UserDTO newStudent = new UserDTO("st@email_1", "1111","nick","name","20020202","์ฌ์","01022222222",
											"์คํ์","1ํ๋",null,null,null);
		Boolean Result = this.userService.singUpStrudent(newStudent);
		log.info("\t + Result : {}", Result);
		
	}//singUpStrudent
	
	@Test
	@Order(4)
	@DisplayName("singUpStrudent ํ์ํ์๊ฐ์")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUpStrudentWithBCryptPassword() throws ServiceException {
		log.trace("singUpStrudentWithBCryptPassword");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@email.net");
		dto.setUser_pw("bcpassword");
		dto.setUser_nick("nickname11");
		dto.setUser_name("๊นํ๊ธ");
		dto.setUser_birth("20220831");
		dto.setUser_gender("๋จ์");
		dto.setUser_phone("0100000000");
		dto.setSt_school("์คํ์");
		dto.setSt_grade("1ํ๋");
		
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
	@DisplayName("singUPTutor ํํฐํ์๊ฐ์")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void singUPTutor() throws ServiceException {
		log.trace("singUPTutor");
		
		UserDTO newTutor = new UserDTO();
		newTutor.setUser_email("childhopp@hanmail.net");
		Boolean Result = this.userService.singUPTutor(newTutor);
		log.info("\t + Result : {}", Result);
		
	}//singUPTutor
	
	@Test
	@Order(5)
	@DisplayName("updateUser ์?์?์?๋ณด์์?")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateUser() throws ServiceException {
		log.trace("updateUser");
		
		UserDTO user = new UserDTO();
		user.setUser_email("STemail_2");
		user.setUser_pw("๋ณ๊ฒฝ11");
		user.setSt_grade("1ํ๋");
		Boolean Result = this.userService.updateUser(user);
		log.info("\t + Result : {}", Result);
		
	}//updateUser
	
	
	@Test
	@Order(6)
	@DisplayName("tutorPass ํํฐ ์น์ธ ํ์ธ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void tutorPass() throws ServiceException {
		log.trace("tutorPass");
		
		String user_email = "childhopp@hanmail.net";
		int Result = this.userService.tutorPass(user_email);
		log.info("\t + Result : {}", Result);
		
	}//tutorPass
	
	
	@Test
	@Order(7)
	@DisplayName("userStatus ์?์? ํํด->์?์ง")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void userStatus() throws ServiceException {
		log.trace("userStatus");
		
		String user_email = "TTemail_5";
		Boolean Result = this.userService.userStatus(user_email);
		log.info("\t + Result : {}", Result);
		
	}//userStatus
	
	@Test
	@Order(8)
	@DisplayName("updateHandGet ์๋ค๊ธฐ ๊ตฌ๋งค, ํ๋")
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
	@DisplayName("updateHandUse ์๋ค๊ธฐ ์ฌ์ฉ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateHandUse() throws ServiceException {
		log.trace("updateHandUse");
		
		Integer h_count = 3;
		String user_email = "ํ์ํ์คํธ";
		Boolean Result = this.userService.updateHandUse(h_count, user_email);
		log.info("\t + Result : {}", Result);
		
	}//updateHandUse
	
	@Test
	@Order(10)
	@DisplayName("selectNicCheck ๋๋ค์์ค๋ณต๊ฒ์ฌ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testselectNicCheck() throws ServiceException {
		log.trace("selectNicCheck");
		
		String nickName1 = "๋๋ํ์";
		String nickName2 = "";
		int resutl1 = this.userService.getNicCheck(nickName1);
		int result2 = this.userService.getNicCheck(nickName2);
		
		log.info("\t + result1 : {}, result2: {}", resutl1, result2);
	}//selectNicCheck
	
	@Test
	@Order(11)
	@Timeout(value = 3, unit = TimeUnit.SECONDS)
	@DisplayName("gettLoginUser ๋ก๊ทธ์ธํ๊ธฐ")
	void gettLoginUser() throws ServiceException {
		log.trace("selectLogin() invoked.");
		
		String user_email="test@email.net";
//		String user_pw = "bcpassword";
		String user_pw = "1";
		
		UserVO vo = this.userService.loginEmail(user_email);
		log.info("\t+ vo : {}", vo );
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if( encoder.matches(user_pw, vo.getUser_pw()) ){	//์ํธํ๋น๋ฐ๋ฒํธ๋ ๊ฐ์ผ๋ฉด
			log.info(" ๋ก๊ทธ์ธ ์ฑ๊ณต " );
		} else { log.info("๋ก๊ทธ์ธ์คํจ"); }
		
	}//selectLogin
	
	@Test
	@DisplayName("loginEmail ๋ก๊ทธ์ธ์?๋ณด ๊ฐ์?ธ์ค๊ธฐ")
	void testloginEmail() throws ServiceException {
		log.trace("loginEmail ๋ก๊ทธ์ธ ์?์? ํ์ธ");
		String user_email="test@email.net";
		UserVO vo = this.userService.loginEmail(user_email);
		log.info("\t+ vo : {}", vo);
	}//loginEmail
	
	@Test
	@DisplayName("findPassword ๋น๋ฐ๋ฒํธ๋ณ๊ฒฝ ")
	void findPassword() throws ServiceException, DAOException  {
		log.trace("findPassword ๋น๋ฐ๋ฒํธ ๋๋ค ๋ณ๊ฒฝ");
		UserDTO dto = new UserDTO();
		dto.setUser_email("tutor2@gmail.com");
		
		UserVO vo = this.userService.loginEmail(dto.getUser_email());

		if( vo==null ) {
			//๋ฑ๋ก๋์ง ์์ ์์ด๋ ์๋๋ค. 
		} else {
			String newPw = "";
			for(int i=0; i<12; i++) {
				newPw += (char) ((Math.random() * 26) + 97);
			}//๋๋ค๋น๋ฐ๋ฒํธ
			
			dto.setUser_pw(newPw);
			int result = this.userMapper.updatePW(dto);
			log.info("\t + ๋น๋ฐ๋ฒํธ ๋ณ๊ฒฝ result : {}", result);
			//์ด๋ฉ์ผ๋ฐ์ก
			//์์๋น๋ฐ๋ฒํธ๊ฐ ์ด๋ฉ์ผ๋ก ๋ฐ์ก๋์์ต๋๋ค. 
		}//if-else
	}//findPassword
	
	@Test
	@DisplayName("getWaitTutor ์น์ธ ๋๊ธฐ ํํฐ ๋ฆฌ์คํธ")
	void getWaitTutor() throws DAOException {
		log.trace("getWaitTutor ์น์ธ ๋๊ธฐ ํํฐ ๋ฆฌ์คํธ");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(10);
		cri.setCurrPage(2);
		List<UserVO> list = this.userMapper.selectWaitTutor(cri);
		list.forEach(log::info);
	}//getWaitTutor
	
	@Test
	@DisplayName("waitTutorCount ์น์ธ ๋๊ธฐ ํํฐ ํ์์")
	void waitTutorCount() throws DAOException {
		log.trace("waitTutorCount ์น์ธ ๋๊ธฐ ํํฐ ํ์์");
		int result = this.userMapper.waitTutorCount();
		log.info("\t + result : {}", result);
		
	}//waitTutorCount
	
	@Test
	@DisplayName("certifiedPhoneNumber")
	void certifiedPhoneNumber() {
		log.trace("certifiedPhoneNumber ํด๋ํฐ์ธ์ฆ๋ฌธ์๋ณด๋ด๊ธฐ");
		this.userService.certifiedPhoneNumber("01089814304", 1234);
		log.info("\t+........");
	}//certifiedPhoneNumber
	
	@Test
	@DisplayName("findEmail")
	void testfindEmail() throws ServiceException {
		log.trace("findEmail ์ด๋ฉ์ผ ์ฐพ๊ธฐ ํ์คํธ");
		String user_phone = "01089814300";
//		String user_phone = "01089814300";
		String result = this.userService.findEmail(user_phone);
		log.info("\t + result : {}", result);
	}//findEmail
	
	@Test
	@DisplayName("findUserEmail")
	void testfindUserEmail() throws ServiceException {
		log.trace("findUserEmail ์ด๋ฉ์ผ ์ค๋ณต์ฒดํฌ ํ์คํธ");
		String userEmail = "abc11@han.com";
		int result = this.userService.findUserEmail(userEmail);
		log.info("\t + result : {}", result);
	}//findUserEmail
	
	@Test
	@DisplayName("kakaoCheck")
	void kakaoCheckTest() {
		log.trace("kakaoCheck ์นด์นด์ค ์?๋ณด ์๋์ง ํ์ธ");
		HashMap<String,Object> param = new HashMap<>();
		param.put("email", "totor@han.net");
		HashMap<String, Object> kakao = this.userService.kakaoCheck(param);
		log.info("\t + kakao : {}", kakao);
	}//kakaoCheckTest
	
	@Test
	@DisplayName("kakaoLogin")
	void kakaoLoginTest() {
		log.trace("kakaoLogin ์นด์นด์ค ๋ก๊ทธ์ธํ๊ธฐ");
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("id", "12333");
		param.put("email", "tutor2@gmail.com");
		UserVO kakao = this.userService.kakaoLogin(param);
		log.info("\t + kakao : {}", kakao);
	}//kakaoLogin
	
	@Test
	@DisplayName("updateKakao")
	void updateKakaoTest() {
		log.trace("updateKakao ์นด์นด์ค ์?๋ณด ์๋ฐ์ดํธ");
		
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("email", "totor@han.net");
		int result = this.userService.updateKakao(param);
		log.info("result : {}", result);
	}//updateKakao
	
	@Test
	@DisplayName("kakaoRegister")
	void kakaoRegister() {
		log.trace("kakaoRegister : ํ์ํ์๊ฐ์");
		
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("email", "111");
		paramMap.put("user_pw", "111");
		paramMap.put("user_nick", "111");
		paramMap.put("user_name", "111");
		paramMap.put("user_birth", "111");
		paramMap.put("user_gender", "111");
		paramMap.put("user_phone", "111");
		paramMap.put("st_school", "111");
		paramMap.put("st_grade", "111");
		paramMap.put("kakaologin", "111111");
		
		
		int result = this.userService.kakaoRegister(paramMap);
		log.info("\r + result : {}", result);
	}//kakaoRegister
	
	@Test
	@DisplayName("kakaoRegisterTu")
	void kakaoRegisterTu() {
		log.trace("kakaoRegisterTu : ํํฐํ์๊ฐ์");
		
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("email", "111");
		paramMap.put("user_pw", "111");
		paramMap.put("user_nick", "111");
		paramMap.put("user_name", "111");
		paramMap.put("user_birth", "111");
		paramMap.put("user_gender", "111");
		paramMap.put("user_phone", "111");
		paramMap.put("tt_school", "111");
		paramMap.put("tt_subject", "111");
		paramMap.put("tt_depart", "111");
		paramMap.put("kakaologin", "111111");
		
		
		int result = this.userService.kakaoRegisterTu(paramMap);
		log.info("\r + result : {}", result);
	}//kakaoRegisterTu
}//end class
