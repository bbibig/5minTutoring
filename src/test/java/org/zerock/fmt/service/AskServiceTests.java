package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
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
import org.zerock.fmt.domain.QuestionBoardDTO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/**/spring/**/*-context.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AskServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private AskService askService;

	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.askService);	
		log.info("\t + this.tutorService: {}", this.askService);
		
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. askService.createQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testcreateQ() throws ServiceException {
		log.trace("질문글 등록 및 손들기 사용");
		
//		손들기가 부족할 때
//		QuestionBoardVO QBvo = new QuestionBoardVO(null, 63, "st@email_1", "이진법이 뭔가요?", "가르쳐주세요.", 0, null, null);
		
//		손들기가 3개 이상일 때
		QuestionBoardDTO QBdto = new QuestionBoardDTO(null, 63, "hi@gmail.com", "근의 공식이 뭔가요?", "가르쳐주세요.", 0, null, null);
		
		boolean createSucceed = this.askService.createQ(QBdto);
		log.info("createSucceed: {}", createSucceed);
		
	} // testcreateQ
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. askService.regUseHand() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testRegUseHand() throws ServiceException {
		log.trace("손들기 사용 정보 등록");
		
		UseHandVO useHandVO = new UseHandVO(null, 42, null, null, "hi@gmail.com");
		
		boolean createSucceed = this.askService.regUseHand(useHandVO);
		log.info("createSucceed: {}", createSucceed);
		
	} // testRegUseHand
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. askService.updateQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateQ() throws ServiceException {
		log.trace("질문글 수정");
		
		// 답변이 있어 수정 불가한 경우
//		QuestionBoardVO QBvo = new QuestionBoardVO(33, null, null, "수정된 질문", "질문입니다.", null, null, null);

		// 수정 가능한 경우
		QuestionBoardDTO QBdto = new QuestionBoardDTO(42, null, null, "수정된 질문", "질문입니다.", null, null, null);
		
		boolean updateSuceed = this.askService.updateQ(QBdto);
		log.info("updateSuceed: {}", updateSuceed);
		
	} // testUpdateQ
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. askService.deleteQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testDeleteQ() throws ServiceException {
		log.trace("질문글 삭제");
		
		// 답변이 있는 경우 삭제 불가
		String qb_number = "8";
		String user_email = "test@gmail.com";
		
		boolean deleteSuceed = this.askService.deleteQ(qb_number, user_email);
		log.info("deleteSuceed: {}", deleteSuceed);
		
	} // testDeleteQ
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. askService.getQB() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetQB() throws ServiceException {
		log.trace("해당 튜터가 받은 질문글 출력");
		
		String tp_number = "63";
		List<QuestionBoardVO> list = this.askService.getQB(tp_number);

		log.info("list: {}", list);
		
	} // testGetQB
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. askService.getOneQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetOneQ() throws ServiceException {
		log.trace("질문글 번호로 질문글 출력");
		
		String qb_number = "24";
		QuestionBoardVO oneQ = this.askService.getOneQ(qb_number);
		
		log.info("oneQ: {}", oneQ);
		
	} // testGetOneQ
	
} // end class
