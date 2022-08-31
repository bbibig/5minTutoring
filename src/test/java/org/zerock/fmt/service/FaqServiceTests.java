package org.zerock.fmt.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
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
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.DAOException;
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
public class FaqServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private FaqService service;
	
	
	//1. 자주묻는 질문과 답 목록 전체 조회 테스트
	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. testGetFaqList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetFaqList() throws ServiceException {
		log.trace("testGetFaqList(), 자주묻는 질문 리스트 전체 조회(페이징)");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		
		List<FaqVO> list = this.service.getFaqList(cri);
		list.forEach(e -> log.info(e));
		
	}//testGetFaqList()
	
	//2. 자주묻는 질문과 답 생성 테스트
	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. testCreateFaq")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testCreateFaq() throws ServiceException {
		log.trace("testCreateFaq(), 자주묻는 질문과 답 생성");
		
		FaqDTO dto = new FaqDTO();
		dto.setAd_id("sohee");
		dto.setFaq_title("자주묻는 질문 제목 서비스 테스트");
		dto.setFaq_content("자주 묻는 질문의 답 서비스 테스트" );
		
		if(this.service.createFaq(dto)) {
			boolean result = this.service.createFaq(dto);
			log.info("\t+ 1. 자주묻는 질문과 답 생성 서비스메소드 결과: {}", result);
			log.info("\t+ 2. dto: {}", dto);
		}// if
		
	}//testCreateFaq()
	
	//3. 자주묻는 질문과 답 수정 테스트
	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. testUpdateFaq")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testUpdateFaq() throws ServiceException {
		log.trace("testUpdateFaq(), 자주묻는 질문과 답 수정");
		
		FaqDTO dto = new FaqDTO();
		dto.setFaq_number(23);
		dto.setFaq_title("자주묻는 질문 제목 서비스 테스트 수정 2");
		dto.setFaq_content("자주 묻는 질문의 답 서비스 테스트 수정 2" );
		
		boolean result = this.service.updateFaq(dto);
		log.info("\t+ 1. 자주묻는 질문과 답 수정 서비스 메소드 결과: {}", result);
		log.info("\t+ 2. dto: {}", dto);
		
	}//testUpdateFaq()
	
	//4. 자주묻는 질문과 답 삭제 테스트
	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. testRemoveFaq")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testRemoveFaq() throws ServiceException {
		log.trace("testRemoveFaq(), 자주묻는 질문과 답 삭제");
		
		FaqDTO dto = new FaqDTO();
		dto.setFaq_number(24);
		
		boolean result = this.service.removeFaq(dto);
		log.info("\t+ 1. 자주묻는 질문과 답 삭제 서비스 메소드 결과: {}", result);
		
	}//testUpdateFaq()

	
	//5. 자주묻는 질문과 답 목록 총 개수 조회 테스트
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. testGetFaqTotal")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetFaqTotal() throws ServiceException {
		log.trace("testGetFaqTotal(), 자주묻는 질문과 답 목록 총 개수");
		
		int faqTotalPage = this.service.getFaqTotal();
		log.info("\t+ 1. 자주묻는 질문과 답 목록 총 개수: {}", faqTotalPage);
		
	}//testGetFaqTotal()
	
}// end class
























