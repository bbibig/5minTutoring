package org.zerock.fmt.mapper;

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

public class InquiryAnswerMapperTests {

	@Setter(onMethod_=@Autowired)
	private InquiryAnswerMapper iaMapper;

//  === 1:1 문의 답변 작성 ===
	@Test
	@Order(1)
	@DisplayName("InquiryAnswerMapper_insert")
	@Timeout(value=5000, unit = TimeUnit.SECONDS)
	void testInsert() throws DAOException {
		log.trace("testInsert() invoked.");

		InquiryAnswerDTO dto = new InquiryAnswerDTO(5, "admin", "답변합니다.", "열심히 해보세요.", null);
		log.info("\t + dto: {}", dto);
	
		int affectedLines = this.iaMapper.insertIA(dto);
		log.info("\t + affectedLines: {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // testInsert
	
	
//  === 특정 1:1 문의 답변 조회 ==== 
	@Test
	@Order(2)
	@DisplayName("특정 1:1문의 답변 조회 테스트")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testSelect() throws DAOException {
		log.trace("testSelect() invoked.");
		
		int iq_number = 5;

		InquiryAnswerVO vo = this.iaMapper.select(iq_number);

		Objects.requireNonNull(vo);
		log.info("/t+ vo: {}", vo);
	
	} // testSelect
	

} // end class