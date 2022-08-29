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
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
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
public class FaqMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private FaqMapper mapper;
	
	
//	//1-1. 자주묻는 질문과 답 목록 전체 조회 테스트
//	@Test
//	@Order(1)
//	@DisplayName("1. testSelectAllFaqList")
//	@Timeout(unit = TimeUnit.SECONDS, value = 10)
//	void testSelectAllFaqList() throws DAOException {
//		log.trace("testSelectAllFaqList(), 자주묻는 질문 리스트 전체 조회");
//		
//		List<FaqVO> list = this.mapper.selectAllFaqList();
//		list.forEach(e -> log.info(e));
//		
//	}//testSelectAllFaqList()
	
	//1-2. 자주묻는 질문과 답 목록 전체 조회 테스트
	@Test
	@Order(5)
	@DisplayName("5. testSelectAllFaqList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllFaqList() throws DAOException {
		log.trace("testSelectAllFaqList(), 자주묻는 질문 리스트 전체 조회(페이징처리)");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		
		List<FaqVO> list = this.mapper.selectAllFaqList(cri);
		list.forEach(e -> log.info(e));
		
	}//testSelectAllFaqList()
	
	//2. 자주묻는 질문과 답 생성 테스트
	@Test
	@Order(2)
	@DisplayName("2. testInsertFaq")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testInsertFaq() throws DAOException {
		log.trace("testInsertFaq(), 자주묻는 질문과 답 생성");
		
						//번호에 0을 넣어도 sql쿼리에선 일단 시퀀스생성으로 처리
		FaqDTO dto = new FaqDTO(0,"sohee", "자주묻는 질문 제목2", "자주묻는 질문의 답2");
		Boolean result =  this.mapper.insertFaq(dto);
		
		log.info("\t+ 2. 자주묻는 질문과 답 생성 결과: {}", result);
		
	}//testInsertFaq()
	
	//3. 자주묻는 질문과 답 수정 테스트
	@Test
	@Order(3)
	@DisplayName("3. testUpdateFaq")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testUpdateFaq() throws DAOException {
		log.trace("testInsertFaq(), 자주묻는 질문과 답 수정");
		
		FaqDTO dto = new FaqDTO(5,"sohee", "자주묻는 질문 제목 수정", "자주묻는 질문의 답 수정");
		Boolean result =  this.mapper.updateFaq(dto);
		
		log.info("\t+ 3. 자주묻는 질문과 답 수정 결과: {}", result);
		
	}//testUpdateFaq()
	
	//4. 자주묻는 질문과 답 삭제 테스트
	@Test
	@Order(4)
	@DisplayName("4. testDeleteFaq")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testDeleteFaq() throws DAOException {
		log.trace("testDeleteFaq(), 자주묻는 질문과 답 삭제");
		
		Boolean result = this.mapper.deleteFaq(4);
		
		log.info("\t+ 4. 자주묻는 질문과 답 삭제 결과: {}", result);
		
	}//testDeleteFaq()
	
	//5. 자주묻는 질문과 답 목록 개수 조회 테스트
	@Test
	@Order(5)
	@DisplayName("5. testetFaqTotal")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetFaqTotalAmount() throws DAOException {
		log.trace("testDeleteFaq(), 자주묻는 질문과 답 목록 총 개수 조회");
		
		Integer faqTotalAmount = this.mapper.getFaqTotalAmount();
		log.info("\t+ 자주묻는 질문과 답 목록 총 개수: {}", faqTotalAmount);
		
	}//testGetFaqTotalAmount()

}// end class
























