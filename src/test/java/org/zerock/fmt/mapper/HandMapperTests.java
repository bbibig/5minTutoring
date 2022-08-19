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
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.HandException;
import org.zerock.fmt.exception.UserException;

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
	
	
	@Test
	@Order(3)
	@DisplayName("  getPayPage  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void getPayPage() throws HandException {
		log.trace("getPayPage() invoked. 구매하기 구매정보조회");
		
		BuyVO payPage = this.handMapper.getPayPage(2);
		
//		BuyVO payPage = new BuyVO(
//								1, 
//								"name_1", 
//								"000-0000-0001",
//								"STemail_1", 
//								2, 
//								"손들기30개", 
//								6600,  
//								2, 
//								13200);
//		
		log.info("\t+ payPage: {}", payPage);
	}//getPayPage
	
	@Test
	@Order(4)
	@DisplayName("4. payPage1 TEST ")
	void payPage1() throws HandException {
		log.trace(" payPage1 손들기 구매페이지 테스트");
		
		String user_email = "STemail_1";
		Integer h_number = 2;
		
		BuyDTO buyTest = this.handMapper.payPage1(user_email, h_number);
		
		log.info("\t + buyTEST : {}", buyTest);
	}//payPage
	
	@Test
	@Order(4)
	@DisplayName("4. payPage2 TEST ")
	void payPage2() throws HandException {
		log.trace(" payPage2 손들기 구매페이지 테스트");
		//결론 : DTO, VO로 넣었더니 파라미터를 못찾는다.. 말이되나 
		UserVO user = new UserVO("STemail_1", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//		user.setUser_email("STemail_1");
		
		HandVO hand = new HandVO(2, "손들기30개", 6600, null);
		
		BuyDTO buyTest = this.handMapper.payPage2(user, hand);
		
		log.info("\t + buyTEST : {}", buyTest);
	}//payPage
	
	@Test
	@Order(5)
	@DisplayName("  updateMyHands  ")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void updateMyHands() throws UserException {
		log.trace("updateMyHands() invoked. 보유손들기 수정");
		
		Integer myWallet = this.handMapper.updateMyHands(30);
		log.info("\t+ myWallet: {}", myWallet);
	}//updateMyHands
	
	
}//end class
