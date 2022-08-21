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
		log.info("\t + 1. this.tutorService: {}", this.tutorService);
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. TutorServie.getAllTInfo() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testGetAllTInfo() throws ServiceException {
		log.trace("튜터페이지 전체 정보 조회");
		
		int tp_number = 7;
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
		
		this.tutorService.getRecentTCard().forEach(log::info);
		
		
	} // testGetRecentTCard
	
	
	
	
	
} // end class
