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
import org.zerock.fmt.domain.TutoringBoardDTO;
import org.zerock.fmt.domain.TutoringBoardVO;
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
public class TutoringServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private TutoringService tutoringService;

	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.tutoringService);	
		log.info("\t + this.tutoringService: {}", this.tutoringService);
		
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. tutoringService.createTutoring() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testCreateTutoring() throws ServiceException {
		log.trace("과외하기 질문 등록 및 손들기 사용");
		
//		손들기가 부족할 때
//		TutoringBoardDTO tbDTO = new TutoringBoardDTO(null, 65, "student1@han.net", "과외요청", "과외요청합니다.");
		
//		손들기가 5개 이상일 때
		TutoringBoardDTO tbDTO = new TutoringBoardDTO(null, 65, "seosujung0@gmail.com", "과외요청", "과외요청합니다.");
		
		boolean createSucceed = this.tutoringService.createTutoring(tbDTO);
		log.info("createSucceed: {}", createSucceed);
	
	} // testCreateTutoring
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. tutoringService.updateTutoring() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateTutoring() throws ServiceException {
		log.trace("과외하기 질문 수정");
		
		TutoringBoardDTO tbDTO = new TutoringBoardDTO(2, null, null, "(수정) 과외요청", "과외 요청합니다.");
		
		boolean updateSucceed = this.tutoringService.updateTutoring(tbDTO);
		log.info("updateSucceed: {}", updateSucceed);
		
	} // testUpdateTutoring
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. tutoringService.deleteTutoring() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testDeleteTutoring() throws ServiceException {
		log.trace("과외하기 질문 삭제");
		
		int tb_number = 5;
		
		boolean deleteSucceed = this.tutoringService.deleteTutoring(tb_number);
		log.info("deleteSucceed: {}", deleteSucceed);
		
	} // testDeleteTutoring
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. tutoringService.updateTBAnswer() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateTBAnswer() throws ServiceException {
		log.trace("답변 완료 상태 변경");
		
		int tb_number = 10;

		boolean updateSucceed = this.tutoringService.updateTBAnswer(tb_number);
		log.info("updateSucceed: {}", updateSucceed);
		
	} // testUpdateTBAnswer
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. tutoringService.getTBQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetTBQ() throws ServiceException {
		log.trace("과외하기 질문 리스트 출력");
		
		int tp_number = 65;
		
		List<TutoringBoardVO> list = this.tutoringService.getTBQ(tp_number);
		list.forEach(log::info);
		
	} // testGetTBQ
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. tutoringService.getOneTBQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetOneTBQ() throws ServiceException {
		log.trace("과외하기 질문 출력");
		
		int tb_number = 2;
		TutoringBoardVO vo = this.tutoringService.getOneTBQ(tb_number);
		log.info("vo: {}", vo);
		
	} // testGetOneTBQ
	

} // end class
