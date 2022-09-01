package org.zerock.fmt.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Objects;
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
import org.zerock.fmt.domain.TutorPageDTO;
import org.zerock.fmt.domain.TutorPageVO;
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
public class TutorMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private TutorMapper tutorMapper;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.tutorMapper);	
		log.info("\t + 1. this.tutorMapper: {}", this.tutorMapper);
		log.info("\t + 2. type: {}", this.tutorMapper.getClass().getName());
	} // injectionTest
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. TutorMapper.selectAllTInfo() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectAllTInfo() throws DAOException {
		log.trace("튜터페이지 전체 정보 조회");
		
		String num = "50";
		
		TutorPageVO tutorPageVO = this.tutorMapper.selectAllTInfo(num);
		
		Objects.requireNonNull(tutorPageVO);
		log.info("\t + tutorPageVO: {}", tutorPageVO);
		
	} // testSelectAllInfo
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. TutorMapper.selectRecentTCard() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectRecentTCard() throws DAOException {
		log.trace("튜터카드 최신순 조회");
		
		List<TutorPageVO> list = this.tutorMapper.selectRecentTCard();
		
		assertNotNull(list);
		list.forEach(log::info);
	} // testSelectRecentTCard
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. TutorMapper.insertIntroInfo() test.")
	void testInsertIntroInfo() throws DAOException {
		log.trace("튜터 소개 입력");
		
		TutorPageDTO newInfo = new TutorPageDTO(
			null, "test@gmail.com", "수학강사 3년 경력", "안녕하세요.", "진심을 다해 가르칩니다.");
		log.info("\t + newInfo: {}", newInfo);
		
		int affectedLines = this.tutorMapper.insertIntroInfo(newInfo);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // insertIntroInfo
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. TutorMapper.updateTInfo() test.")
	void testUpdateTInfo() throws DAOException {
		log.trace("튜터 소개 수정");
		
		TutorPageDTO newInfo = new TutorPageDTO(
				7, null, "수학과외 5년", "안녕하세요.", "진심을 다해 가르칩니다.");
		log.info("\t + newInfo: {}", newInfo);
		
		int affectedLines = this.tutorMapper.updateTInfo(newInfo);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // testUpdateTInfo
} // end class
