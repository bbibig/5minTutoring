package org.zerock.fmt.service;

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
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class MypageHandServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageHandService service;
	
	@Setter(onMethod_= @Autowired)
	private BuyService buyService;
	
		
	//1-1. 손들기 사용 목록 조회 페이징 처리(학생)
	@Test
	@Order(1)
	@DisplayName("1. getAllMyUsehandtList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getAllMyUsehandtList() throws ServiceException {
		log.trace("손들기 사용 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		cri.setDateFrom("2022-09-06");
		cri.setDateTo("2022-09-14");
		
		List<UseHandVO2> list = this.service.getAllMyUsehandtList(cri);
		list.forEach(e -> log.info(e));
		
	}//getAllMyUsehandtList()
	
	//1-2. 손들기 사용 목록 총 횟수 조회
	@Test
	@Order(2)
	@DisplayName("2. getMyUsehandTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getMyUsehandTotalAmount() throws ServiceException {
		log.trace("손들기 사용 목록 총 횟수 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		cri.setDateFrom("2022-09-06");
		cri.setDateTo("2022-09-14");
		
		int result = this.service.getMyUsehandTotalAmount(cri);
		log.info("\t+ 손들기 사용 총 횟수: {}", result);
		
	}//getMyUsehandTotalAmount()

	//2-1. 손들기 구매 목록 조회 페이징 처리(학생)
	@Test
	@Order(3)
	@DisplayName("3. myPageBuyinfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void myPageBuyinfo() throws ServiceException {
		log.trace("손들기 구매 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<BuyVO> list = this.buyService.myPageBuy(cri);
		list.forEach(e -> log.info(e));
		
	}//myPageBuyinfo()
	
	//2-2. 손들기 구매 목록 총 횟수 조회
	@Test
	@Order(4)
	@DisplayName("4. myPageBuyCount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void myPageBuyCount() throws ServiceException {
		log.trace("손들기 구매 목록 총 횟수 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		int result = this.buyService.myPageBuyCount(dto.getUser_email());
		log.info("\t+ 손들기 구매 총 횟수: {}", result);
		
	}//myPageBuyCount()
	
	
}// end class
























