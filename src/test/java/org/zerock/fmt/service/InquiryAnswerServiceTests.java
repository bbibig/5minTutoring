package org.zerock.fmt.service;

import java.util.Objects;
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
import org.zerock.fmt.domain.InquiryAnswerDTO;
import org.zerock.fmt.domain.InquiryAnswerVO;
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
public class InquiryAnswerServiceTests {

	@Setter(onMethod_= @Autowired) 
	private InquiryAnswerService iaService;
	
	@Test
	@Order(1)
	@DisplayName("1:1 문의 답변 작성 테스트")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testCreateIA() throws ServiceException {
		log.trace("testCreateIA() invoked.");   

		InquiryAnswerDTO dto = new InquiryAnswerDTO(40, "suin", "답변입니다.", "일대일 문의 답변 테스트", null, "Y");
		
		boolean result = this.iaService.createIA(dto);
		log.info("일대일 문의 답변 작성 결과: {}", result);
			
	} // testCreateIA 
	

	@Test
	@Order(2)
	@DisplayName("1:1 문의 답변 조회 테스트")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testGetIA() throws ServiceException {
		log.trace("testgetIA() invoked.");
		
		int iq_number = 13;
		InquiryAnswerDTO dto = new 	InquiryAnswerDTO();
		dto.setIq_number(iq_number);

		InquiryAnswerVO vo = this.iaService.getIA(iq_number);
		Objects.requireNonNull(vo);
		log.info("/t+ vo: {}", vo);
	} // testGetIA
	
} // end class
