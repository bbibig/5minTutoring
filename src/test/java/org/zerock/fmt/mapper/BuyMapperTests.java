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
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
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
	@Timeout(value=10, unit = TimeUnit.SECONDS)
	void selectPayPage() throws DAOException {
		log.trace("selectPayPage() invoked.");
		
		String user_email = "test@gmail.com";
		Integer h_number = 3;
		
		BuyDTO payPage = this.buyMapper.selectPayPage(user_email, h_number);
		
		log.info("\t+ payPage : {}", payPage);
	} // selectPayPage
	
	@Test
	@Order(2)
	@Timeout(value=10, unit = TimeUnit.SECONDS)
	void insertBuyHands() throws DAOException {
		log.trace("insertBuyHands() invoked.");
		
		BuyDTO buyHands = new BuyDTO("test@gmail.com", 4, 1, 19800);
	
		log.info("\t+ buyHands: {}", buyHands);
		
		int affectedLines = this.buyMapper.insertBuyHands(buyHands);
		
		log.info("\t+ affectedLines: {}", affectedLines);
		
	} // insertBuyHands
	
	@Test
	@Order(3)
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	void selectMyPayPage() throws DAOException {
		log.trace("selectMyPayPage() invoked.");
		
		BuyDTO myPayPage = this.buyMapper.selectMyPayPage(5);
		
		log.info("\t+ myPayPage: {}", myPayPage);
	} // selectMyPayPage
	
	
	@Test
	@DisplayName("selectAllBuy")
	void selectAllBuy() throws DAOException {
		log.trace("selectAllBuy 어드민 판매내역 조회");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(10);
		cri.setCurrPage(1);
		cri.setPagesPerPage(3);
		List<BuyVO> list = this.buyMapper.selectAllBuy(cri);
		list.forEach(log::info);
	}//selectAllBuy
	
	@Test
	@DisplayName("countAllBuy")
	void countAllBuy() throws DAOException {
		log.trace("countAllBuy 구매내역 총 개수");
		int result = this.buyMapper.countAllBuy();
		log.info("\t + result : {}", result);
	}//countAllBuy
	
	@Test
	@DisplayName("selectAllSale")
	void selectAllSale() throws DAOException {
		log.trace("selectAllSale 총 판매금액");
		int result = this.buyMapper.selectAllSale();
		log.info("\t + result : {}", result);
	}//selectAllSale
	
} // end class
