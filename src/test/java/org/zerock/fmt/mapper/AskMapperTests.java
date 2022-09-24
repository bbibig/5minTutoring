package org.zerock.fmt.mapper;

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
public class AskMapperTests {

	@Setter(onMethod_= @Autowired)
	private AskMapper askMapper;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.askMapper);	
		log.info("\t + 1. this.askMapper: {}", this.askMapper);
		log.info("\t + 2. type: {}", this.askMapper.getClass().getName());
	} // injectionTest
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. AskMapper.insertQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void insertQTest() throws DAOException {
		log.trace("질문글 작성");
		
		QuestionBoardDTO newQ = new QuestionBoardDTO(null, 42, "hi@gmail.com", "문법을 잘모르겠어요", "궁금합니다.", 0, null, null);

		int affectedLines = this.askMapper.insertQ(newQ);
		
		log.info("newQ : {}", newQ);
		log.info("affectedLines : {}", affectedLines);

	} // insertQTest
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. AskMapper.updateQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateQTest() throws DAOException {
		log.trace("질문글 수정");
		
		QuestionBoardDTO revisedQ = new QuestionBoardDTO(32, null, null, "미적분 질문합니다.", "미적분 고함수의 활용 그래프 개형 그릴 때 함수의 분모 분자를 로피탈해서 x를 무한대로 보낸다는게 이해가 안가요. 설명 부탁드립니다.", null, null, null);
		
		int affectedLines = this.askMapper.updateQ(revisedQ);
		
		log.info("revisedQ : {}", revisedQ);
		log.info("affectedLines : {}", affectedLines);
		
	} // updateQTest
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. AskMapper.deleteQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void deleteQTest() throws DAOException {
		log.trace("질문글 삭제");
		
		String qb_number = "35";
		
		int affectedLines = this.askMapper.deleteQ(qb_number);
		log.info("affectedLines : {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // deleteQTest
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. AskMapper.selectQB() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void selectQBTest() throws DAOException {
		log.trace("해당 튜터의 질문글 출력");
		
		String tp_number = "63";
		
		List<QuestionBoardVO> list = this.askMapper.selectQB(tp_number);
		assertNotNull(list);
		list.forEach(log::info);
	
	} // selectQBTest
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. AskMapper.selectOneQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void selectOneQTest() throws DAOException {
		log.trace("질문글 출력");
		
		String qb_number = "24";
		
		QuestionBoardVO Qvo = this.askMapper.selectOneQ(qb_number);
		log.info("Qvo: {}", Qvo);
		
	} // selectOneQTest
	
	@Test
	@Order(6)
	@DisplayName("countQeustion")
	void countQeustion() throws DAOException {
		log.trace("countQeustion : 총 질문글 개수");
		 
		int result1 = this.askMapper.countQeustion(65,"hi@gmail.com");
		int result2 = this.askMapper.countQeustion(65,"seosujung0@gmail.com");
		log.info("result 1: {}, result2 : {}", result1, result2);
	}//countQeustion
} // end class
