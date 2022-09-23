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
import org.zerock.fmt.domain.TutoringBoardDTO;
import org.zerock.fmt.domain.TutoringBoardVO;
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
public class TutoringMapperTests {

	@Setter(onMethod_= @Autowired)
	private TutoringMapper tutoringMapper;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.tutoringMapper);	
		log.info("\t + 1. this.tutoringMapper: {}", this.tutoringMapper);
		log.info("\t + 2. type: {}", this.tutoringMapper.getClass().getName());
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. TutoringMapper.insertTutoring() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void insertTutoringTest() throws DAOException {
		log.trace("과외하기 질문글 등록");
		
		TutoringBoardDTO newDTO = new TutoringBoardDTO(null, 65, "seosujung0@gmail.com", "과외하기 질문", "과외하기 질문입니다.");

		int affectedLines = this.tutoringMapper.insertTutoring(newDTO);
		
		log.info("newDTO : {}", newDTO);
		log.info("affectedLines : {}", affectedLines);

	} // insertTutoringTest
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. TutoringMapper.updateTutoring() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateTutoringTest() throws DAOException {
		log.trace("과외하기 질문글 수정");
		
		TutoringBoardDTO updateDTO = new TutoringBoardDTO(3, null, null, "(수정) 과외하기 질문", "과외하기 질문입니다.");
		
		int affectedLines = this.tutoringMapper.updateTutoring(updateDTO);
		
		log.info("updateDTO : {}", updateDTO);
		log.info("affectedLines : {}", affectedLines);
		
	} // updateTutoringTest
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. TutoringMapper.deleteTutoring() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void deleteTutoringTest() throws DAOException {
		log.trace("과외하기 질문글 삭제");
		
		int tb_number = this.tutoringMapper.deleteTutoring(3);
		
		int affectedLines = this.tutoringMapper.deleteTutoring(tb_number);
		log.info("affectedLines : {}", affectedLines);
		
	} // deleteTutoringTest
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. TutoringMapper.updateTBAnswer() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateTBAnswerTest() throws DAOException {
		log.trace("과외하기 답변 여부 변경");
		
		int tb_number = this.tutoringMapper.updateTBAnswer(2);
		
		int affectedLines = this.tutoringMapper.updateTBAnswer(tb_number);
		log.info("affectedLines : {}", affectedLines);
		
	} // updateTBAnswerTest
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. TutoringMapper.selectTBQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void selectTBQTest() throws DAOException {
		log.trace("과외하기 질문 리스트 출력");
		
		int tp_number = 65;
		
		List<TutoringBoardVO> list = this.tutoringMapper.selectTBQ(tp_number);
		list.forEach(log::info);
		
	} // selectTBQTest
	
//	@Disabled
	@Test
	@Order(6)
	@DisplayName("6. TutoringMapper.selectOneTBQ() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void selectOneTBQTest() throws DAOException {
		log.trace("과외하기 질문 리스트 출력");
		
		int tb_number = 8;
		
		TutoringBoardVO tbVO = this.tutoringMapper.selectOneTBQ(tb_number);
		log.info("tbVO : {}", tbVO);
		
	} // selectOneTBQTest
	
	
	
} // end class
