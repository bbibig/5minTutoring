package org.zerock.fmt.mapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
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
import org.zerock.fmt.exception.HandException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		})
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class HandMapperTests {

	@Setter(onMethod_= {@Autowired})
	private HandMapper handMapper;

	@Test
	@Order(1)
	@DisplayName("  selectAllHands  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectAllHands() throws HandException {
		log.trace("selectAllHands() invoked. 전체 상품 조회");
		
		List<HandVO> list = this.handMapper.selectAllHands();
		list.forEach(log::info);
	}//selectAllHands
	
	@Test
	@Order(2)
	@DisplayName("  selectHand  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectHand() throws HandException {
		log.trace("selectHand() invoked. 상품별 조회");
		
		HandVO hand = this.handMapper.selectHand(2);
		log.info("\t+ hand: {}", hand);
	}//selectAllHands
	
	
}//end class
