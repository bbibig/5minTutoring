package org.zerock.fmt.service;

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
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReviewServiceTest {

	@Setter(onMethod_ = @Autowired)
	private ReviewService revireService;
	
	@Test
	@Order(1)
	@DisplayName("createReview")
	void testCreateReview() throws ServiceException {
		log.trace("CreateReview : 리뷰등록 테스트");
		
		ReviewDTO dto = new ReviewDTO();
		dto.setTp_number(64);
		dto.setUser_email("hi@gmail.com");
		dto.setRv_star(3.5);
		dto.setRv_content("답변을 너무 늦게 줍니다... ");
		int result = this.revireService.createReview(dto);
		log.info("\t + result : {}", result);
	}//createReview
	
	@Test
	@Order(2)
	@DisplayName("getReview")
	void testGetReview() throws ServiceException {
		log.trace("getReview : 리뷰 목록 테스트");
		CriteriaReview	cri = new CriteriaReview();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setTp_number(64);
		List<ReviewVO> list = this.revireService.getReview(cri);
		list.forEach(log::info);
	}//getReview
	
	@Test
	@Order(3)
	@DisplayName("countList")
	void testCountList() throws ServiceException {
		log.trace("countList : 페이징");
		int result = this.revireService.countList(64);
		log.info("\t + result : {}", result);
	}//countList
	
	@Test
	@Order(4)
	@DisplayName("modifyReview")
	void testmodifyReview() throws ServiceException {
		log.trace("modifyReview : 리뷰 수정 테스트");
		ReviewDTO dto = new ReviewDTO();
		dto.setRv_content("수정합니다... 엄청 잘 가르쳐 줍니다.^0^");
		dto.setRv_number(1);
		dto.setRv_star(4.0);
		dto.setTp_number(64);
		int result = this.revireService.modifyReview(dto);
		log.info("\t + result : {}", result);
	}//modifyReview
	
	@Test
	@Order(5)
	@DisplayName("avgReview")
	void testavgReview() throws ServiceException {
		log.trace("avgReview : 리뷰 평균 테스트");
		
		int tp_number = 65;
		double result = this.revireService.avgReview(tp_number);
		log.info("\t + result : {}", result);
	}//avgReview
}//end class
