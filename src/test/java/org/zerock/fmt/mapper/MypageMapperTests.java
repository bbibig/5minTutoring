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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
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
public class MypageMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageMapper mapper;
	
	
	//1. 기본정보 조회(특정 회원)
	@Test
	@Order(1)
	@DisplayName("1. testSelectUser")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectUser() throws DAOException {
		log.trace("testSelectUser(), 마이페이지 기본정보 조회");
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");		
		
		UserVO vo = mapper.selectUser(dto);
		log.info("\t+ 기본정보 조회 {}", vo);
	}//testSelectUser()
	
	
	//9. 회원 DB 비밀번호 조회
	@Test
	@Order(9)
	@DisplayName("9. 회원 DB 비밀번호 조회")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectUserDbPw() throws DAOException {
		log.trace("회원 DB 비밀번호 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		String userDbPw = mapper.selectUserDbPw(dto.getUser_email());
		log.info("\t+ 회원 DB 비밀번호");
				
	}//회원 DB 비밀번호 조회
	
	
	//2. 기본정보 수정
	@Test
	@Order(2)
	@DisplayName("2. TestUpdateStudentInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void TestUpdateStudentInfo() throws DAOException {
		log.trace("TestselectUser(), 마이페이지 기본정보 수정(학생)");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		String paramPw = "pw1234";
		String bcPw = encoder.encode(paramPw);
		dto.setUser_pw(bcPw);		
		
		boolean result = mapper.updateUserInfo(dto);
		log.info("\t+ 기본정보 수정결과: {}", result);
		
	}//TestUpdateStudentInfo()
	
	
	//3. 나의 질문글 목록 조회
	@Test
	@Order(3)
	@DisplayName("3. testSelectAllQuestionList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllQuestionList() throws DAOException {
		log.trace("testSelectAllQuestionList(), 마이페이지 나의 질문글 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<QuestionBardVO> list = mapper.selectAllMyQuestionList(cri);
		list.forEach(e -> log.info(e));
		
	}//testSelectAllQuestionList()
	
	//4. 나의 질문글 목록 총 개수
	@Test
	@Order(4)
	@DisplayName("4. testGetMyQuestionTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyQuestionTotalAmount() throws DAOException {
		log.trace("testSelectAllQuestionList(), 마이페이지 나의 질문글 목록 총 개수 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		Integer amount = mapper.getMyQuestionTotalAmount(dto.getUser_email());
		log.info("\t + 나의 질문글 총 개수: {}", amount);
		
	}//testGetMyQuestionTotalAmount()
	
	//5. 나의 작성글 목록 조회
	@Test
	@Order(5)
	@DisplayName("5. testSelectAllMyCommunitytList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllMyCommunitytList() throws DAOException {
		log.trace("testSelectAllMyCommentList(), 마이페이지 나의 작성글 목록 조회");

		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<CommunityVO> list = mapper.selectAllMyCommunitytList(cri);
		list.forEach(e -> log.info(e));
		
	}//testSelectAllMyCommunitytList()
	
	//6. 나의 작성글 총 개수
	@Test
	@Order(6)
	@DisplayName("6. testGetMyCommunityTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyCommunityTotalAmount() throws DAOException {
		log.trace("testGetMyCommentTotalAmount(), 마이페이지 나의 작성글 총 개수 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		Integer amount = mapper.getMyCommunityTotalAmount(dto.getUser_email());
		log.info("\t + 나의 작성글 총 개수: {}", amount);
		
	}//testGetMyCommunityTotalAmount()
	
	//7. 나의 댓글 목록 조회
	@Test
	@Order(7)
	@DisplayName("7. testSelectAllMyCommentList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllMyCommentList() throws DAOException {
		log.trace("testSelectAllMyCommentList(), 마이페이지 나의 댓글 목록 조회");

		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<CommentVO> list = mapper.selectAllMyCommentList(cri);
		list.forEach(e -> log.info(e));
		
	}//testSelectAllMyCommentList()
	
	//8. 나의 댓글 목록 총 개수
	@Test
	@Order(8)
	@DisplayName("8. testGetMyCommentTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyCommentTotalAmount() throws DAOException {
		log.trace("testGetMyCommentTotalAmount(), 마이페이지 나의 댓글 목록 총 개수 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		Integer amount = mapper.getMyCommentTotalAmount(dto.getUser_email());
		log.info("\t + 나의 댓글 총 개수: {}", amount);
		
	}//testGetMyQuestionTotalAmount()


}// end class






















