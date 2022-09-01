package org.zerock.fmt.service;

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
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/**/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TutorServiceTests {

	@Setter(onMethod_=@Autowired)
	private TutorService tutorService;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.tutorService);	
		log.info("\t + this.tutorService: {}", this.tutorService);
		
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. TutorServie.getAllTInfo() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetAllTInfo() throws ServiceException {
		log.trace("해당 튜터페이지의 전체 정보 조회");
		
		String tp_number = "50";
		TutorPageVO tutorPageVO = this.tutorService.getAllTInfo(tp_number);
		
		Objects.requireNonNull(tutorPageVO);
		log.info("\t + tutorPageVO: {}", tutorPageVO);
		
	} // testGetAllTInfo
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. TutorServie.getRecentTCard() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetRecentTCard() throws ServiceException {
		log.trace("튜터카드 최신순 조회");
		
		List<TutorPageVO> list = this.tutorService.getRecentTCard();
		assertNotNull(list);
		
		log.info("\t + list: {}", list);
		
	} // testGetRecentTCard
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. TutorServie.createIntroInfo() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testCreateIntroInfo() throws ServiceException {
		log.trace("튜터 소개 입력");
		
		TutorPageDTO newDTO = new TutorPageDTO(null, "test1", "수학강사 13년 경력", "안녕하세요.", "수학은 기초부터");
		
		boolean createSucceed = this.tutorService.createIntroInfo(newDTO);
		log.info("updateSucceed: {}", createSucceed);
		
	} // testupdateTInfo
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. TutorServie.updateTInfo() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateTInfo() throws ServiceException {
		log.trace("튜터 소개 입력");
		
		TutorPageDTO newDTO = new TutorPageDTO(26, null, "수학과외 4년", "안녕하세요.", "수학은 기초부터");
		
		boolean updateSucceed = this.tutorService.updateTInfo(newDTO);
		log.info("updateSucceed: {}", updateSucceed);
		
	} // testupdateTInfo
	
} // end class
