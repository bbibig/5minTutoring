package org.zerock.fmt.mapper;


import java.util.ArrayList;
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
		dto.setTp_number(65);
		dto.setUser_email("now@han.net");
		dto.setRv_star(2);
		dto.setRv_content("너무 건성으로 가르쳐 주는 것 같아여");
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
		dto.setRv_star(5);
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
	
	@Test
	@Order(6)
	@DisplayName("selectRVone")
	void testSelectRVone() throws DAOException {
		log.trace("selectRVone : 리뷰 정보 가져오기");
		
		ReviewVO rvOne1 = this.reviewMapper.selectRVone(2);
		ReviewVO rvOne2 = this.reviewMapper.selectRVone(100);
		log.info(" Review : {}, {}", rvOne1, rvOne2);
		
	}//selectRVone
	
	@Test
	@Order(7)
	@DisplayName("updateAve")
	void testUpdateAge() throws DAOException {
		log.trace("updateAve : 튜터 페이지 평균 업데이트");
		
		int result1 = this.reviewMapper.updateAve(3.0, 61);
		int result2 = this.reviewMapper.updateAve(3.0, 100);
		log.info("\t + result : {}, {}",result1, result2);
	}//updateAve
	
	@Test
	@Order(8)
	@DisplayName("deleteReview")
	void testDeleteReview() throws DAOException {
		log.trace("deleteReview : 리뷰 삭제 테스트");

		int result1 = this.reviewMapper.deleteReview(23);
		int result2 = this.reviewMapper.deleteReview(300);
		log.info("\t + result : {}, {}", result1, result2);
	}//deleteReview
	
	@Test
	@Order(9)
	@DisplayName("countReview")
	void testcountReview() throws DAOException {
		log.trace("countReview : 별점 개수 구하기");
		
		List<Integer> stars = new ArrayList<>();
		
		stars.add(this.reviewMapper.countReview(5,65));
		stars.add(this.reviewMapper.countReview(4,65));
		stars.add(this.reviewMapper.countReview(3,65));
		stars.add(this.reviewMapper.countReview(2,65));
		stars.add(this.reviewMapper.countReview(1,65));

		stars.forEach(log::info);

	}//countReview
}//end class
