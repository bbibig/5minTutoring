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
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.domain.WithdrawalVO2;
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
	
	@Setter(onMethod_= @Autowired)  
	private UserService userService;
	
	// 출금 신청과 동시에 사용자가 보유한 손들기가 차감 되도록
	@Test
	@Order(1)
	@DisplayName("튜터 출금신청 테스트") 
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testCreateWithdrawal() throws ServiceException {
		log.trace("testCreateWithdrawal() invoked.");

		WithdrawalDTO dto = new WithdrawalDTO(null, "now@han.net", "오분은행 1234-55-6789", 140, null, null, null, null);
		int h_count = dto.getW_quantity();
		String user_email = dto.getUser_email();
		
		boolean result = this.wService.createWithdrawal(dto);
		log.info("손들기 신청 결과: {}", result);
	
		boolean result2 = this.userService.updateHandUse(h_count, user_email);
		log.info("손들기 차감 결과: {}", result2); 
		
	} // testCreateWithdrawal
	
	@Test
	@Order(2)
	@DisplayName("관리자 출금 신청 내역 목록 조회 - 승인 대기") 
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testGetAllWithdrawalList() throws ServiceException {
		log.trace("testGetAllWithdrawalList() invoked.");

		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setApproval("대기");
		List<WithdrawalVO> list = this.wService.getAllWithdrawalList(cri);
		list.forEach(e -> log.info(e));

	} // testGetAllWithdrawalList
	
	@Test
	@Order(6)
	@DisplayName("관리자 출금 신청 내역 목록 조회 - 승인 완료") 
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void testGetAllWithdrawalOkList() throws ServiceException {
		log.trace("testGetAllWithdrawalList() invoked.");

		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setApproval("완료");
		List<WithdrawalVO> list = this.wService.getAllWithdrawalList(cri);
		list.forEach(e -> log.info(e));

	} // testGetAllWithdrawalOkList
	
	@Test
	@Order(3)
	@DisplayName("출금 내역 페이징 - 어드민")
	void countList() throws ServiceException {
		log.trace("countList : 어드민 페이징");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setApproval("완료");
		int result = this.wService.countList(cri);
		log.info("\t + result : {}" , result);
	} //countList
	
	// 승인 여부 수정 (승인 완료 / 승인 대기)
	@Test
	@Order(4)
	@DisplayName("출금 승인 여부 업데이트")
	void testUpdateState() throws ServiceException {
		log.trace("testUpdateState() invoked.");
	
		WithdrawalDTO dto = new WithdrawalDTO(41, null, null, null, null, "승인 완료", null, null);
		
		boolean result = this.wService.updateState(dto);
		log.info("result: {}", result);
	} // testUpdateState
	
//	@Test
//	@Order(5)
//	@DisplayName("출금 신청 손들기 개수 차감")
//	void testUpdateHands() throws ServiceException {
//		log.trace("testUpdateHands() invoked.");
//	
//		UserDTO dto = new UserDTO();
//		log.info("\t + dto: {}", dto);
//		
//		boolean result = this.wService.updateHands("now@han.net");
//		log.info("result: {}", result);
//	} // testUpdateHands

	@Test
	@Order(6)
	@DisplayName("승인 여부 총 금액")
	void totalDrawal() throws ServiceException {
		log.trace("totalDrawal : 승인 여부 별 총 금액");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setApproval("완료");
		int result1 = this.wService.totalDrawal(cri);
		log.trace("result : {}",result1);
	}//totalDrawal
	
	@Test
	@Order(7)
	@DisplayName("출금 내역 상세 조회")
	void testGetWithdrawal() throws ServiceException {
		log.trace("testGetWithdrawal() invoked.");
	
		int w_num = 88;
		WithdrawalVO2 vo = wService.getWithdrawal(w_num);
		log.info("Withdrawalvo: {}", vo);
	} // testGetWithdrawal
	
} // end class
