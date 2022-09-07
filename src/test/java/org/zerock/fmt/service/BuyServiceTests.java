package org.zerock.fmt.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
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
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.exception.DAOException;
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
	@Timeout(value=10, unit = TimeUnit.SECONDS)
	void buyHands() throws ServiceException {
		log.info("buyHands() invoked.");
		
		BuyDTO buyHands = new BuyDTO("test@gmail.com", 4, 1, 19800);
		
		log.info("\t+ buyHands: {}", buyHands);
		
		int affectedLines = this.buyService.buy(buyHands);
		
		log.info("\t+ affectedLines: {}", affectedLines);
		
	} // buyHands

	@Test
	@DisplayName("myPageBuyinfo")
	void myPageBuy() throws ServiceException {
		log.trace("myPageBuyinfo 마이페이지 - 구매내역정보");
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setAmount(6);
		cri.setCurrPage(1);
		cri.setPagesPerPage(1);
		cri.setUser_email("test@gmail.com");
		List<BuyVO> list = this.buyService.myPageBuy(cri);
		list.forEach(log::info);
	}//myPageBuyinfo
	
	@Test
	@DisplayName("myPageBuyCount")
	void myPageBuyCount() throws ServiceException {
		log.trace("myPageBuyCount 마이페이지 - 총 구매건수");
		String user_email="test@gmail.com";
		int result = this.buyService.myPageBuyCount(user_email);
		log.info("\t+ result : {}", result);
	}//myPageBuyCount
	
	@Test
	@DisplayName("myPageBuyinfo")
	void myPageBuyinfo() throws ServiceException {
		log.trace("myPageBuyinfo 마이페이지 - 구매내역 상세조회");
		int b_number = 8;
		BuyInfoVO info = this.buyService.myPageBuyinfo(b_number);
		log.info("\t + info : {}", info);
	}//myPageBuyinfo
	
	@Test
	@DisplayName("selectAllBuy")
	void selectAllBuy() throws ServiceException {
		log.trace("selectAllBuy 전체 조회 테스트");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(10);
		cri.setCurrPage(1);
		cri.setPagesPerPage(3);
		List<BuyVO> list = this.buyService.selectAllBuy(cri);
		list.forEach(log::info);
		
	}//selectAllBuy
	
	@Test
	@DisplayName("countBuy")
	void countBuy() throws ServiceException {
		log.trace("countBuy 총 구매 건수 테스트");
		int result = this.buyService.countBuy();
		log.info("\t + result : {}", result);
	}//countBuy
	
	@Test
	@DisplayName("selectAllSale")
	void selectAllSale() throws ServiceException {
		log.trace("selectAllSale 총 판매 금액 테스트");
		int result = this.buyService.countSale();
		log.info("\t + result : {}", result);
	}//selectAllSale
} // end class
