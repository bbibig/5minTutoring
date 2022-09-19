package org.zerock.fmt.mapper;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReviewMapperTest {

	
	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;
	
	@Test
	@Order(1)
	@DisplayName("insertReview")
	void testInsertReview() throws DAOException {
		log.trace("InsertReview : 리뷰등록 테스트");
		
		ReviewDTO dto = new ReviewDTO();
		dto.setTp_number(64);
		dto.setUser_email("학생가입");
		dto.setRv_star(5.0);
		dto.setRv_content("시간을 예약 할 수 있었으면 좋겠습니다~~");
		int result = this.reviewMapper.InsertReview(dto);
		log.info("\t + result : {}", result);	
	}//insertReview
	
	@Test
	@Order(2)
	@DisplayName("selectReview")
	void testselectReview() throws DAOException {
		log.trace("selectReview : 리뷰 목록 테스트");
		CriteriaReview cri = new CriteriaReview();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setTp_number(64);
		List<ReviewVO> list = this.reviewMapper.selectReview(cri);
		list.forEach(e->log.info(e));
	}//selectReview
	
	@Test
	@Order(3)
	@DisplayName("countList")
	void testCountList() throws DAOException {
		log.trace("countList : 리뷰 목록 페이징");
		int tp_number1 = 11;
		int tp_number2 = 64;
		int result1 = this.reviewMapper.countList(tp_number1);
		int result2 = this.reviewMapper.countList(tp_number2);
		log.info("\t + result 1: {}, result2 : {}", result1, result2);
	}//countList
	
	@Test
	@Order(4)
	@DisplayName("updateReview")
	void testUpdateReview() throws DAOException {
		log.trace("updateReview : 리뷰 수정 테스트");
		ReviewDTO dto = new ReviewDTO();
		dto.setRv_content("*** 리뷰 수정중 ***");
		dto.setRv_star(5.0);
		dto.setRv_number(6);
		dto.setTp_number(64);
		int result = this.reviewMapper.updateReview(dto);
		log.info("\t + result : {}", result);
	}//updateReview
	
	@Test
	@Order(5)
	@DisplayName("avgReview")
	void testAvgReview() throws DAOException {
		log.trace("avgReview : 리뷰 평균 테스트");
		
		double result1 = this.reviewMapper.avgReview(65);
		double result2 = this.reviewMapper.avgReview(63);
		log.info("\t + result : {}, {}", result1, result2);
	}//avgReview
}//end class
