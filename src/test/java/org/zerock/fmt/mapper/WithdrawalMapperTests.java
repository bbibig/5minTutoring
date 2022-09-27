package org.zerock.fmt.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
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
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.domain.WithdrawalVO2;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= "file:src/main/webapp/**/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class WithdrawalMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private WithdrawalMapper withdrawalMapper;
	
	@BeforeAll
	void beforeAll() {	// 1회성 전처리 작업: 필드 주입이 잘 되었는지 확인용
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.withdrawalMapper);
		log.info("\t+ 1. this.inquirymapper: {}", this.withdrawalMapper);
		log.info("\t+ 2. type: {}", this.withdrawalMapper.getClass().getName());
	} // beforeAll
	
	
//  [C] 출금 신청
	@Test
	@Order(1)
	@DisplayName("출금신청 테스트")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testInsertWithdrawal() throws DAOException {
		log.trace("testInsertWithdrawal() invoked."); 

		WithdrawalDTO dto = new WithdrawalDTO(null, "now@han.net", "오분은행 1111-2222", 600, 75000, null, null, null);
		log.info("\t + dto: {}", dto);
	
		int affectedLines = this.withdrawalMapper.insertWithdrawal(dto);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // testInsert
	
	
//  [R] 출금 신청 내역 조회(승인 대기) - 관리자 
	@Test
	@Order(2)
	@DisplayName("출금 신청 내역조회 테스트")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGetList() throws DAOException {
		log.trace("testGetList() invoked.");
		
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setApproval("대기");
		List<WithdrawalVO> list = this.withdrawalMapper.selectAllWithdrawalList(cri);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	
	} // testGetList 
	
//  [R] 출금 신청 내역 조회(승인 완료) - 관리자
	@Test
	@Order(3)
	@DisplayName("출금 신청 내역조회 테스트")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGetOkList() throws DAOException {
		log.trace("testGetList() invoked.");
		
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setApproval("완료");
		List<WithdrawalVO> list = this.withdrawalMapper.selectAllWithdrawalList(cri);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	
	} // testGetOkList 
	

//	[R] 페이징 총 건수 - 관리자
	@Test
	@Order(4)
	@DisplayName("어드민 페이지 총 개수")
	void countList() throws DAOException {
		log.info("countList : 어드민 페이징");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setApproval("완료");
		int result = this.withdrawalMapper.countList(cri);
		
		log.info("\t + result : {}", result);
	} //countList
	
	
	
//  [U] 출금 신청 승인 여부 변경 (승인 대기 / 승인 완료)  	
	@Test
	@Order(5)
	@DisplayName("출금 신청 승인 변경")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testUpdate() throws DAOException {
		log.trace("testUpdate() invoked.");
		
		WithdrawalDTO dto = new WithdrawalDTO(61, null, null, null, null, "승인 완료", null, null);
		
		int affectedLines =  this.withdrawalMapper.updateState(dto);
		log.info("\t+ affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // testUpdate
	
//  [U] 손들기 개수 차감
//	@Test
//	@Order(6)
//	@DisplayName("손들기 개수 차감")
//	@Timeout(value=100, unit=TimeUnit.SECONDS)
//	void testUpdateHands() throws DAOException {
//		log.trace("testUpdateHands() invoked.");
//			
//		UserDTO dto = new UserDTO();
//		log.info("\t + dto: {}", dto);
//			
//		int affectedLines =  this.withdrawalMapper.updateHands("now@han.net");
//		log.info("\t+ affectedLines: {}", affectedLines);
//		
//		assert affectedLines == 1;
//		
//	} // testUpdateHands
	
	@Test
	@Order(5)
	@DisplayName("승인별 금액")
	void totalDrowal() throws DAOException {
		log.trace("totalDrowal : 승인 여부 별 총 금액");
		
		CriteriaAdmin cri1 = new CriteriaAdmin();
		cri1.setApproval("대기");
		int result1 = this.withdrawalMapper.totalDrowal(cri1);
		
		CriteriaAdmin cri2 = new CriteriaAdmin();
		cri1.setApproval("완료");
		int result2 = this.withdrawalMapper.totalDrowal(cri2);
		
		CriteriaAdmin cri3 = new CriteriaAdmin();
		int result3 = this.withdrawalMapper.totalDrowal(cri3);
		
		log.info("\t + result : {}, {}, {}", result1, result2, result3);
	}//totalDrowal
	
	@Test
	@Order(6)
	@DisplayName("출금 신청 조회")
	void testSelectWithdrawal() throws DAOException {
		log.trace("testSelectWithdrawal : 특정 출금 신청 조회");
		
		int w_num = 88;		

		WithdrawalVO2 vo = this.withdrawalMapper.selectWithdrawal(w_num);

		Objects.requireNonNull(vo); 
		log.info("/t+ vo: {}", vo);
	}//totalDrowal
	
} // end class
