package org.zerock.fmt.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class WithdrawalServiceTests {
	
	@Setter(onMethod_= @Autowired)  
	private WithdrawalService wService;
	
	@Test
	@Order(1)
	@DisplayName("튜터 출금신청 테스트") 
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testCreateWithdrawal() throws ServiceException {
		log.trace("testCreateWithdrawal() invoked.");

		WithdrawalDTO dto = new WithdrawalDTO(null, "test@gmail.com", "오분은행 1234-55-6789", 100, 1800, null, null);
		
		boolean result = this.wService.createWithdrawal(dto);
		log.info("일대일 문의 작성 결과: {}", result);
		
	} // testCreateWithdrawal
	
	@Test
	@Order(2)
	@DisplayName("출금 신청 내역 목록 조회 - 관리자") 
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testGetAllWithdrawalList() throws ServiceException {
		log.trace("testGetAllWithdrawalList() invoked.");

		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(22);
		cri.setCurrPage(1);
		List<WithdrawalVO> list = this.wService.getAllWithdrawalList(cri);
		list.forEach(e -> log.info(e));

	} // testGetAllWithdrawalList
	
	@Test
	@Order(3)
	@DisplayName("출금 내역 페이징 - 어드민")
	void countList() throws ServiceException {
		log.trace("countList : 어드민 페이징");
		int result = this.wService.countList(null);
		log.info("\t + result : {}" , result);
	} //countList
	
	// 승인 여부 수정 (승인 완료 / 승인 대기)
	@Test
	@Order(4)
	@DisplayName("출금 승인 여부 업데이트")
	void testUpdateState() throws ServiceException {
		log.trace("testUpdateState() invoked.");
	
		WithdrawalDTO dto = new WithdrawalDTO(14, null, null, null, null, "승인 완료", null);
		
		boolean result = this.wService.updateState(dto);
		log.info("result: {}", result);
	} // testUpdateState
	
	
} // end class
