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
import org.zerock.fmt.domain.AnswerVO2;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class MypageHandMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageHandMapper mapper;
	
	//1. 손들기 사용 목록 조회
	@Test
	@Order(1)
	@DisplayName("1. selectAllmyUsehandList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectAllmyUsehandList() throws DAOException {
		log.trace("selectAllmyUsehandList(), 마이페이지 손들기 사용 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		cri.setDateFrom("2022-09-05");
		cri.setDateTo("2022-09-06");
		
		List<UseHandVO2> list = mapper.selectAllmyUsehandQList(cri);
		list.forEach(e -> log.info(e));

	}//selectAllmyUsehandList()
	
	//2. 손들기 사용 목록 총 개수
	@Test
	@Order(2)
	@DisplayName("2. getMyUsehandTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getMyUsehandTotalAmount() throws DAOException {
		log.trace("testGetMyCommentTotalAmount(), 마이페이지 손들기 사용 목록 총 개수 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		cri.setDateFrom("2022-09-05");
		cri.setDateTo("2022-09-06");
		
		Integer amount = mapper.getMyUsehandQTotalAmount(cri);
		log.info("\t + 손들기 사용 총 횟수: {}", amount);
		
	}//getMyUsehandTotalAmount()
	
	//3. 손들기 구매 목록 조회
	@Test
	@Order(3)
	@DisplayName("3. myPageAllBuy")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void myPageAllBuy() throws DAOException {
		log.trace("myPageAllBuy(), 마이페이지 손들기 구매 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		cri.setDateFrom("2022-08-31");
		cri.setDateTo("2022-09-07");
		
		List<BuyVO> list = this.mapper.myPageAllBuy(cri);
		list.forEach(e -> log.info(e));

	}//myPageAllBuy()
	
	//4. 손들기 구매 목록 총 개수
	@Test
	@Order(4)
	@DisplayName("4. myPageBuyCount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void myPageBuyCount() throws DAOException {
		log.trace("myPageBuyCount(), 마이페이지 손들기 구매 목록 총 개수 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		cri.setDateFrom("2022-08-31");
		cri.setDateTo("2022-09-07");
		
		Integer amount = this.mapper.myPageBuyCount(cri);
		log.info("\t + 손들기 구매 총 횟수: {}", amount);
		
	}//myPageBuyCount()
	
	//5. 손들기 구매내역 상세조회
	@Test
	@Order(5)
	@DisplayName("selectBuyDetail")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectBuyDetail() throws DAOException {
		log.trace("selectBuyDetail(), 마이페이지 손들기 구매 상세내역 조회");
		
		BuyInfoVO vo = this.mapper.selectBuyDetail(12);
		log.info("\t+ 구매 상세내역: {}", vo);
		
	}//selectBuyDetail()
	
	//6. 손들기 출금 내역 목록 조회
	@Test
	@Order(6)
	@DisplayName("손들기 출금 내역 목록 조회")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectAllMyWithdrawalList() throws DAOException {
		log.trace("손들기 출금 내역 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("abc@han.net");
		cri.setDateFrom("2022-09-10");
		cri.setDateTo("2022-09-18");
		
		List<WithdrawalVO> list = mapper.selectAllMyWithdrawalList(cri);
		list.forEach(e -> log.info(e));

	}//selectAllMyWithdrawalList()
	
	//7. 손들기 출금 내역 총 수량
	@Test
	@Order(7)
	@DisplayName("손들기 출금 내역 총 수량")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getMyWithdrawalTotalAmount() throws DAOException {
		log.trace("손들기 출금 내역 총 수량 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("abc@han.net");
		cri.setDateFrom("2022-09-10");
		cri.setDateTo("2022-09-18");
		
		Integer amount = mapper.getMyWithdrawalTotalAmount(cri);
		log.info("\t + 손들기 출금 내역 총 횟수: {}", amount);
		
	}//getMyWithdrawalTotalAmount()
	
	
	//8. 손들기 획득 내역 목록 조회
	@Test
	@Order(8)
	@DisplayName("손들기 획득 내역 목록 조회")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectAllmyGetHandQList() throws DAOException {
		log.trace("손들기 획득 내역 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("abc@han.net");
		cri.setDateFrom("2022-09-12");
		cri.setDateTo("2022-09-18");
		
		List<AnswerVO2> list = mapper.selectAllmyGetHandQList(cri);
		list.forEach(e -> log.info(e));

	}//selectAllmyGetHandQList()
	
	//9. 손들기 획득 내역 총 수량
	@Test
	@Order(9)
	@DisplayName("손들기 획득 내역 총 수량")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getMyGetHandQTotalAmount() throws DAOException {
		log.trace("손들기 획득 내역 총 수량 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("abc@han.net");
		cri.setDateFrom("2022-09-12");
		cri.setDateTo("2022-09-18");
		
		Integer amount = mapper.getMyGetHandQTotalAmount(cri);
		log.info("\t + 손들기 획득 내역 총 횟수: {}", amount);
		
	}//getMyGetHandQTotalAmount()

	
	//10. 튜터페이지 조회
	@Test
	@Order(10)
	@DisplayName("튜터페이지 번호")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getTutorPageNum() throws DAOException {
		log.trace("튜터 페이지 번호");

		Integer pageNum = this.mapper.getTutorPageNum("abc@han.net");
		log.info("튜터페이지 번호: {}", pageNum);
		
	}//getMyGetHandQTotalAmount()
}// end class






















