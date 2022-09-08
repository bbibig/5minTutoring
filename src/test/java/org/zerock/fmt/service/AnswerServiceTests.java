package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.zerock.fmt.domain.AnswerDTO;
import org.zerock.fmt.domain.AnswerVO;
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
public class AnswerServiceTests {

	@Setter(onMethod_ = @Autowired)
	private AnswerService answerService;

	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.answerService);	
		log.info("\t + this.tutorService: {}", this.answerService);
		
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. answerService.createA() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testCreateA() throws ServiceException {
		log.trace("답변 등록 및 답변 상태 변경");
		
		AnswerDTO newA = new AnswerDTO(7, "test@gmail.com", "답변글 등록");

		boolean createSucceed = this.answerService.createA(newA);
		log.info("createSucceed: {}", createSucceed);
		
	} // testCreateA
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. answerService.updateA() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateA() throws ServiceException {
		log.trace("답변 수정");
		
		AnswerDTO updatedA = new AnswerDTO(7, "test@gmail.com", "수정된 답변");
		
		boolean updateSucceed = this.answerService.updateA(updatedA);
		log.info("updateSucceed: {}", updateSucceed);
		
	} // testUpdateA
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. answerService.getA() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetA() throws ServiceException {
		log.trace("해당 질문에 대한 답변 출력");
		
		String qb_number = "7";
		
		AnswerVO answerVo = this.answerService.getA(qb_number);
		log.info("answerVo: {}", answerVo);
		
	} // testGetA
	
	
	
} // end class
