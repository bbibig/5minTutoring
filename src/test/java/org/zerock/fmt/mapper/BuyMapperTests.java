package org.zerock.fmt.mapper;


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
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class BuyMapperTests {

	@Setter(onMethod_= {@Autowired})
	private BuyMapper buyMapper;
	
	@Test
	@Order(1)
	@Timeout(value=5, unit = TimeUnit.SECONDS)
	void payPage() throws DAOException {
		log.trace("payPage() invoked.");
		
		String user_email = "STemail_1";
		Integer h_number = 2;
		
		BuyDTO buyTest = this.buyMapper.payPage(user_email, h_number);
		
		log.info("\t+ buyTest : {}", buyTest);
	} // payPage
	
	
	
	
	
} // end class
