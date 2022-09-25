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
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
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
public class InquiryQuestionServiceTests {

	@Setter(onMethod_= @Autowired)  
	private InquiryQuestionService iqService;
	 
	@Test
	@Order(1)
	@DisplayName("1:1 문의 작성 테스트") 
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testCreateIQ() throws ServiceException {
		log.trace("testCreateIQ() invoked.");

		InquiryQuestionDTO dto = new InquiryQuestionDTO(null, "test@gmail.com", "문의합니다.", "일대일 문의 작성 테스트", null, null);
		
		boolean result = this.iqService.createIQ(dto);
		log.info("일대일 문의 작성 결과: {}", result);
		
	} // testCreateIA
	
	@Test
	@Order(2)
	@DisplayName("1:1 문의 조회 테스트 - 답변완료")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testGetAllInquiryYList() throws ServiceException {
		log.trace("testGetAllInquiryYList() invoked.");
		
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(2);
		cri.setCurrPage(1);
		List<InquiryQuestionVO> list = this.iqService.getAllInquiryYList(cri);
		list.forEach(e -> log.info(e));
	} // testGetAllInquiryYList
	
	@Test
	@Order(3)
	@DisplayName("1:1 문의 조회 테스트 - 미답변")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testGetAllInquiryNList() throws ServiceException {
		log.trace("testGetAllInquiryNList() invoked.");
		
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(10);
		cri.setCurrPage(1);
		List<InquiryQuestionVO> list = this.iqService.getAllInquiryNList(cri);
		list.forEach(e -> log.info(e));
	} // testGetAllInquiryNList
	
	@Test
	@DisplayName("페이징 - 어드민")
	void countList() throws ServiceException {
		log.trace("countList : 어드민 페이징");
		int result1 = this.iqService.countList("Y");
		int result2 = this.iqService.countList("N");
		log.info("\t + result 1 : {}, result2 :{}" , result1, result2);
	}//countList
	
	@Test 
	@Order(4)
	@DisplayName("특정 1:1 문의 조회 테스트 ")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testGetInquiry() throws ServiceException {
		log.trace("testGetInquiry() invoked.");
		
		int iq_number = 15;
		InquiryQuestionVO vo = this.iqService.getInquiry(iq_number);
		log.info("\t+ vo: {}", vo);

	} // testGetInquiry

	@Test
	@Order(5)
	@DisplayName("답변 여부 업데이트")
	void testUpdateInquiryState() throws ServiceException {
		log.trace("testUpdateInquiryState() invoked.");
	
		InquiryQuestionDTO dto = new InquiryQuestionDTO(57, null, null, null, null, "Y");
		
		boolean result = this.iqService.updateInquiryState(57);
		log.info("result: {}", result);
	} // testUpdateInquiryState
	
} // end class
