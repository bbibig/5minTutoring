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
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
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
public class MypageServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageService service;
	
	
	//1. 기본정보 테스트
	@Test
	@Order(1)
	@DisplayName("1. testGetUserInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetFaqList() throws ServiceException {
		log.trace("TestGetUserInfo(), 마이페이지 기본정보 조회 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		UserVO vo = this.service.getUserInfo(dto);
		log.info("\t+ 기본정보: {}", vo);
		
	}//TestGetUserInfo()

	//9. 회원 DB 비밀번호 조회
	@Test
	@Order(9)
	@DisplayName("9. 회원 DB 비밀번호 조회")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetUserDbPw() throws ServiceException {
		log.trace("회원 DB 비밀번호 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		String userDbPw = this.service.getUserDbPw(dto.getUser_email());
		log.info("\t+ 회원 DB 비밀번호: {}", userDbPw);
		
	}//회원 DB 비밀번호 조회
	
	//1-1. 기본정보 테스트
	@Test
	@Order(4)
	@DisplayName("4. testModifyUserInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testUpdateUserInfo() throws ServiceException {
		log.trace("testModifyUserInfo(), 마이페이지 기본정보 수정 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		dto.setUser_pw("1111111q");
		dto.setUser_name("서수정");
		dto.setUser_nick("crystal003");
		dto.setUser_phone("01050956053");
		
		boolean result = this.service.modifyUserInfo(dto);
		log.info("\t+ 수정 결과: {}", result);
		
	}//testModifyUserInfo()
	
	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(2)
	@DisplayName("2. testGetAllMyQuestionList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllMyQuestionList() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 질문글 목록 조회 테스트");
		
//		String user_email = "test@gmail.com";
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<QuestionBoardVO> list = this.service.getAllMyQuestionList(cri);
		list.forEach(e -> log.info(e));
		
	}//testGetAllMyQuestionList()
	
	//2-2. 나의 질문글 목록 총 개수 획득
	@Test
	@Order(3)
	@DisplayName("3. testGetMyQuestionTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyQuestionTotalAmount() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 질문글 목록 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		int result = this.service.getMyQuestionTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 질문글 총 개수: {}", result);
		
	}//testGetMyQuestionTotalAmount()
	
	
	//3-1. 나의 댓글 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(5)
	@DisplayName("5. testGetAllMyCommunityList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllMyCommunityList() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 작성글 목록 조회 테스트");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<CommunityVO> list = this.service.getAllMyCommunityList(cri);
		list.forEach(e -> log.info(e));
		
	}//testGetAllMyCommunityList()
	
	//3-2. 나의 작성글 총 개수 획득
	@Test
	@Order(6)
	@DisplayName("6. getMyCommunityTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getMyCommunityTotalAmount() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 작성글 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		int result = this.service.getMyCommunityTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 작성글 총 개수: {}", result);
		
	}//getMyCommunityTotalAmount()
	
	
	//4-1. 나의 댓글 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(7)
	@DisplayName("7. testGetAllMyCommentList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllMyCommentList() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 댓글 목록 조회 테스트");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<CommentVO> list = this.service.getAllMyCommentList(cri);
		list.forEach(e -> log.info(e));
		
	}//testGetAllMyCommentList()
	
	//4-2. 나의 댓글 목록 총 개수 획득
	@Test
	@Order(8)
	@DisplayName("8. testGetMyCommentTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyCommentTotalAmount() throws ServiceException {
		log.trace("TestGetUserInfo(), 나의 댓글 목록 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		int result = this.service.getMyCommentTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 댓글 총 개수: {}", result);
		
	}//testGetMyCommentTotalAmount()
	
	// 5. 1:1 문의하기
	@Test
	@Order(10)
	@DisplayName("10.testInquiryCreate") 
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testCreateIQ() throws ServiceException {
		log.trace("testCreateIQ(), 일대일 문의 작성 테스트");

		InquiryQuestionDTO dto = new InquiryQuestionDTO(null, "test@gmail.com", "문의합니다.", "일대일 문의 작성 테스트", null, "N");
		
		boolean result = this.service.createIQ(dto);
		log.info("\t+ 일대일 문의 작성 결과: {}", result);
		
	} // testCreateIA
	
	// 5-1. 나의 1:1 문의글 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(11)
	@DisplayName("11. testGetAllInquiryList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllInquiryList() throws ServiceException {
		log.trace("testGetAllInquiryList(), 나의 문의 목록 조회 테스트");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<InquiryQuestionVO> list = this.service.getAllMyInquiryList(cri);
		list.forEach(e -> log.info(e));
		
	} // testGetAllInquiryList
	
	// 5-2. 나의 1:1 문의글 목록 총 개수 획득
	@Test
	@Order(12)
	@DisplayName("12. testGetMyInquiryTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyInquiryTotalAmount() throws ServiceException {
		log.trace("testGetMyInquiryTotalAmount(), 나의 문의글 목록 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("tutor2@gmail.com");
		
		int result = this.service.getMyInquiryTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 문의글 총 개수: {}", result);
		
	} // testGetMyInquiryTotalAmount
	
	// 5-3. 나의 1:1 문의 & 답변 조회
	@Test
	@Order(13)
	@DisplayName("13. testGetMyInquiry")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyInquiry() throws ServiceException {
		log.trace("testGetMyInquiry(), 나의 문의와 답변 조회 테스트");
		
		InquiryQuestionDTO dto = new InquiryQuestionDTO(); 
		dto.setIq_number(50);		

		InquiryVO vo = service.getMyInquiry(dto.getIq_number());
		log.info("\t+ 일대일 문의와 답변 {}", vo);
		
	} // testGetMyInquiry
	
	
	// 6. 손들기 출금 신청 하기 (튜터)
	@Test
	@Order(14)
	@DisplayName("14. testCreateWithdrawal")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testCreateWithdrawal() throws ServiceException {
		log.trace("testCreateWithdrawal(), 튜터 손들기 출금 신청 테스트");

		WithdrawalDTO dto = new WithdrawalDTO(null, "tt@han.net3", "오분은행 22222-44444-5555", 1000, 22000, "승인 대기 중", null);
		log.info("\t + dto: {}", dto);
	
		boolean result = this.service.createWithdrawal(dto);
		log.info("\t+ 튜터 출금 신청 결과: {}", result);
		
	} // testCreateWithdrawal
	
	// 6-1. 나의 손들기 출금 신청 목록 조회 페이징 처리(내림차순으로)
	@Test
	@Order(15)
	@DisplayName("15. testGetAllMyWithdrawalList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetAllMyWithdrawalList() throws ServiceException {
		log.trace("testGetAllMyWithdrawalList(), 나의 손들기 출금 신청 목록 조회 테스트");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("now@han.net");
		
		List<WithdrawalVO> list = this.service.getAllMyWithdrawalList(cri);
		list.forEach(e -> log.info(e));
		
	} // testGetAllMyWithdrawalList
	
	// 6-2. 나의 출금 신청 목록 총 개수 조회
	@Test
	@Order(16)
	@DisplayName("16. testGetMyWithdrawalTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyWithdrawalTotalAmount() throws ServiceException {
		log.trace("testGetMyWithdrawalTotalAmount(), 나의 출금 신청 목록 총 개수 획득 테스트");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("tutor2@gmail.com");
		
		int result = this.service.getMyWithdrawalTotalAmount(dto.getUser_email());
		log.info("\t+ 나의 출금 신청 목록 총 개수: {}", result);
		
	} // testGetMyWithdrawalTotalAmount
	
	
	// 7. 탈퇴하기
	@Test
	@Order(17)
	@DisplayName("17. testUserStatus")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testUserStatus() throws ServiceException {
		log.trace("testUserStatus(), 회원 탈퇴(정지) 테스트");
		
		String user_email = "tutor1@han.net";
		Boolean Result = this.service.userStatus(user_email);
		log.info("\t+ 회원 탈퇴 결과 : {}", Result);
		
	} // testUserStatus
	
}// end class
























