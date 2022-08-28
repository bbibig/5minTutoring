package org.zerock.fmt.service;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class BuyServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BuyService buyService;

	@Test
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void getPayPage() throws ServiceException {
		log.info("getPayPage() invoked.");
		
		String user_email = "STemail_1";
		Integer h_number = 2;
		
		BuyDTO payPage = this.buyService.getPayPage(user_email, h_number);
		
		log.info("\t+ payPage: {}", payPage);
		
	} // getPayPage
	
	@Test
	@Timeout(value=10, unit = TimeUnit.SECONDS)
	void buyHands() throws ServiceException {
		log.info("buyHands() invoked.");
		
		BuyDTO buyHands = new BuyDTO("test@gmail.com", 4, 1, 19800);
		
		log.info("\t+ buyHands: {}", buyHands);
		
		int affectedLines = this.buyService.buy(buyHands);
		
		log.info("\t+ affectedLines: {}", affectedLines);
		
	} // buyHands
	
	@Test
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	void getMyPayPage() throws ServiceException {
		log.trace("selectMyPayPage() invoked.");
		
		BuyDTO myPayPage = this.buyService.getMyPayPage(5);
		
		log.info("\t+ myPayPage: {}", myPayPage);
	} // getMyPayPage
	
	
} // end class
