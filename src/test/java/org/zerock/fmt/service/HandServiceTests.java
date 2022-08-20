package org.zerock.fmt.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class HandServiceTests {

	@Setter(onMethod_= {@Autowired})
	private HandService handService;
		
	@Test
	@Order(1)
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void getAllHands() throws ServiceException {
		log.info("getAllHands() invoked.");
		
		List<HandVO> list = this.handService.getAllHands();
		list.forEach(log::info);

	} // getAllHands
	
	
	@Test
	@Order(2)
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void getHand() throws ServiceException {
		log.info("getHand() invoked.");
		
		HandVO hand = this.handService.getHand(3);
		log.info("\t+ hand: {}", hand);
		
	} // getHand
	
	
} // end class
