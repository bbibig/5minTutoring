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
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
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
public class InquiryQuestionMapperTests {

	@Setter(onMethod_=@Autowired)
	private InquiryQuestionMapper iqMapper;
	 
	@BeforeAll
	void beforeAll() {	// 1회성 전처리 작업: 필드 주입이 잘 되었는지 확인용
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.iqMapper);
		log.info("\t+ 1. this.inquirymapper: {}", this.iqMapper);
		log.info("\t+ 2. type: {}", this.iqMapper.getClass().getName());
	} // beforeAll
	
	
	
//	=== 1:1 문의하기 작성 === 
	@Test
	@Order(1)
	@DisplayName("1:1 문의 작성 테스트")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testInsert() throws DAOException {
		log.trace("testInsert() invoked.");

		InquiryQuestionDTO dto = new InquiryQuestionDTO(null, "test@gmail.com", "문의합니다.", "구매한 손들기도 환불이 되나요?", null, "N");
		log.info("\t + dto: {}", dto);
	
		int affectedLines = this.iqMapper.insertIQ(dto);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
	} // testInsert
	
	
	
// 	=== 1:1 문의 목록조회 (답변 완료) ===
	@Test
	@Order(2)
	@DisplayName("1:1문의 목록조회 테스트")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGetYList() throws DAOException {
		log.trace("testGetYList() invoked.");
		
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		List<InquiryQuestionVO> list = this.iqMapper.selectAllInquiryYList(cri);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	} // testGetYList 
	
	// 	=== 1:1 문의 목록조회 (미답변) ===
	@Test
	@Order(3)
	@DisplayName("1:1문의 목록조회 테스트")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGetNList() throws DAOException {
		log.trace("testGetNList() invoked.");
		
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(2);
		cri.setPagesPerPage(3);
		List<InquiryQuestionVO> list = this.iqMapper.selectAllInquiryNList(cri);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	} // testGetNList 
	
	@Test
	@DisplayName("어드민 페이지 총 개수")
	void countList() throws DAOException {
		log.info("countList : 어드민 페이징");
		int result1 = this.iqMapper.countList("Y");
		int result2 = this.iqMapper.countList("N");
		log.info("\t + result : {}, {}", result1, result2);
	}//countList
	
//  === 특정 1:1 문의 조회 ==== 
	@Test
	@Order(4)
	@DisplayName("1:1문의 조회 테스트")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testSelectInquiry() throws DAOException {
		log.trace("testSelectInquiry() invoked.");
		
		int iq_number = 89;		

		InquiryQuestionVO vo = this.iqMapper.selectInquiry(iq_number);

		Objects.requireNonNull(vo); 
		log.info("/t+ vo: {}", vo);
	
	} // testSelectInquiry
	
//  === 답변 상태 수정(미답변:N / 답변완료:Y) ==== 	
	@Test
	@Order(5)
	@DisplayName("1:1문의 답변상태 수정")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testUpdate() throws DAOException {
		log.trace("testUpdate() invoked.");
		
		InquiryQuestionDTO dto = new InquiryQuestionDTO(53, null, null, null, null, "Y");
		
		int affectedLines =  this.iqMapper.updateInquiryState(dto);
		log.info("\t+ affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // testUpdate
	
	
} // end class
