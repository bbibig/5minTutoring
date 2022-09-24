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
import org.zerock.fmt.domain.CommentVO3;
import org.zerock.fmt.domain.CommunityVO2;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
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
	
	@Setter(onMethod_= @Autowired)
	private UserMapper userMapper;
	
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
		dto.setUser_email("test1@gmail.com");
		String paramPw = "1111111q";
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
		
		List<QuestionBoardVO> list = mapper.selectAllMyQuestionList(cri);
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
		
		List<CommunityVO2> list = mapper.selectAllMyCommunitytList(cri);
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
		
		List<CommentVO3> list = mapper.selectAllMyCommentList(cri);
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


	// 10. 1:1 문의하기 작성
	@Test
	@Order(10)
	@DisplayName("10. testInsertInquiry")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testInsertInquiry() throws DAOException {
		log.trace("testInsertInquiry() invoked.");

		InquiryQuestionDTO dto = new InquiryQuestionDTO(null, "sd456@gmail.com", "문의합니다.", "출금신청을 했는데 언제 처리되나요?", null, "N");
		log.info("\t + dto: {}", dto);
	
		int affectedLines = this.mapper.insertIQ(dto);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
	
	} // testInsertInquiry
	
	// 11. 나의 1:1 문의글 목록 조회 
	@Test
	@Order(11)
	@DisplayName("11. testSelectAllMyInquiryList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllMyInquiryList() throws DAOException {
		log.trace("testSelectAllMyInquiryList() invoked.");

		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("sd456@gmail.com");
		
		List<InquiryQuestionVO> list = mapper.selectAllMyInquiryList(cri);
		list.forEach(e -> log.info(e));
	
	} // testSelectAllMyInquiryList
	
	// 12. 나의 1:1 문의글 총 개수
	@Test
	@Order(12)
	@DisplayName("12. testGetMyInquiryTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyInquiryTotalAmount() throws DAOException {
		log.trace("testGetMyInquiryTotalAmount(), 마이페이지 나의 문의글 목록 총 개수 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("tutor2@gmail.com");
		
		Integer amount = mapper.getMyInquiryTotalAmount(dto.getUser_email());
		log.info("\t + 나의 문의글 총 개수: {}", amount);
		
	} // testGetMyInquiryTotalAmount
	
	// 13. 나의 1:1 문의글과 답변 조회
	@Test
	@Order(13)
	@DisplayName("13. testSelectMyInquiry")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectMyInquiry() throws DAOException {
		log.trace(" testSelectMyInquiry() invoked.");
		
		InquiryQuestionDTO dto = new InquiryQuestionDTO(); 
		dto.setIq_number(50);		

		InquiryVO vo = mapper.selectMyInquiry(dto.getIq_number());
		log.info("\t+ 일대일 문의와 답변 {}", vo);
	
	} // testSelectMyInquiry
	

	// 14. 출금 신청 - 튜터
	@Test
	@Order(14)
	@DisplayName("14. testInsertWithdrawal")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testInsertWithdrawal() throws DAOException {
		log.trace("testInsertWithdrawal() invoked.");

		WithdrawalDTO dto = new WithdrawalDTO(null, "tt@han.net3", "오분은행 22222-44444-5555", 500, 77000, "승인 대기", null, null);
		log.info("\t + dto: {}", dto);
	
		int affectedLines = this.mapper.insertWithdrawal(dto);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
	
	} // testInsertInquiry
	
	// 15. 나의 손들기 출금 신청 목록 조회
	@Test
	@Order(15)
	@DisplayName("15. testSelectAllMyWithdrawalList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllMyWithdrawalList() throws DAOException {
		log.trace("testSelectAllMyWithdrawalList() invoked.");

		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("tutor2@gmail.com");
		
		List<WithdrawalVO> list = mapper.selectAllMyWithdrawalList(cri);
		list.forEach(e -> log.info(e));
	
	} // testSelectAllMyWithdrawalList
	
	// 16. 나의 출금 신청 목록 총 개수
		@Test
		@Order(16)
		@DisplayName("16. testGetMyInquiryTotalAmount")
		@Timeout(unit = TimeUnit.SECONDS, value = 10)
		void testGetMyWithdrawalTotalAmount() throws DAOException {
			log.trace("testGetMyWithdrawalTotalAmount(), 마이페이지 나의 출금 신청 목록 총 개수");
			
			UserDTO dto = new UserDTO();
			dto.setUser_email("tutor2@gmail.com");
			
			Integer amount = mapper.getMyWithdrawalTotalAmount(dto.getUser_email());
			log.info("\t + 나의 문의글 총 개수: {}", amount);
			
		} // testGetMyWithdrawalTotalAmount
	
	
	// 17. 회원 탈퇴(정지) 상태 변경 
	@Test
	@Order(17)
	@DisplayName("17. testStopUser")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testStopUser() throws DAOException {

		String user_email = "sd456@gmail.com";
		Integer updateResult = this.userMapper.updateUserStop(user_email);
		
		if(updateResult==1) {
			log.info("\t updateResult : {}", updateResult);
			log.info("활동정지 완료");
		} else log.info("테스트실패"); 
		
	} // testStopUser
	
}// end class






















