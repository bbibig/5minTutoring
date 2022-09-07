package org.zerock.fmt.mapper;

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
import org.zerock.fmt.domain.AnswerVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/**/spring/**/*-context.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private AnswerMapper answerMapper;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
			
		assertNotNull(this.answerMapper);	
		log.info("\t + 1. this.answerMapper: {}", this.answerMapper);
		log.info("\t + 2. type: {}", this.answerMapper.getClass().getName());
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. AnswerMapper.insertA() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void insertATest() throws DAOException {
		log.trace("답변글 작성");
		
		AnswerVO newA = new AnswerVO(null, 33, "답변입니다.", null, null);
		
		int affectedLines = this.answerMapper.insertA(newA);
		
		log.info("affectedLines : {}", affectedLines);
	} // insertATest
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. AnswerMapper.updateA() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateATest() throws DAOException {
		log.trace("답변글 수정");
		
		AnswerVO revisedA = new AnswerVO(null, 33, "안녕하세요, 답변입니다.", null, null);
		
		int affectedLines = this.answerMapper.updateA(revisedA);
		
		log.info("affectedLines : {}", affectedLines);
	} // updateATest
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. AnswerMapper.selectA() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void selectATest() throws DAOException {
		log.trace("답변글 출력");
		
		String qb_number = "33";
		
		AnswerVO Avo = this.answerMapper.selectA(qb_number);
		
		log.info("Avo : {}", Avo);
	} // selectATest
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. AnswerMapper.updateAStatus() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateAStatusTest() throws DAOException {
		log.trace("답변 상태 변경");
		
		String qb_number = "36";
		
		int affectedLines = this.answerMapper.updateAStatus(qb_number);
		log.info("affectedLines : {}", affectedLines);
		
	} // updateAStatusTest
	
} // end class
