package org.zerock.fmt.service;

import java.util.List;
import java.util.Map;

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
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.ProfileMapper;
import org.zerock.fmt.mapper.ReviewMapper;

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
		
	@Setter(onMethod_ = @Autowired)
	private ProfileService profileService;
	
	@Test
	@Order(1)
	@DisplayName("createReview")
	void testCreateReview() throws ServiceException {
		log.trace("CreateReview : 리뷰등록 테스트");
		
		ReviewDTO dto = new ReviewDTO();
		dto.setTp_number(64);
		dto.setUser_email("hi@gmail.com");
		dto.setRv_star(3);
		dto.setRv_content("답변을 너무 늦게 줍니다... ");
		int result = this.revireService.createReview(dto);
		log.info("\t + result : {}", result);
	}//createReview
	
	@Test
	@Order(2)
	@DisplayName("getReview + 프로필VO 포함")
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
		dto.setRv_star(4);
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
	
	@Test
	@Order(6)
	@DisplayName("getReviewDetail")
	void getReviewDetail() throws ServiceException {
		log.trace("getReviewDetail : 리뷰 상세 정보");

		ReviewVO review1 = this.revireService.getRevirwDetail(1);
		ReviewVO review2 = this.revireService.getRevirwDetail(100);
		log.info("reviews : {}, {}", review1, review2);
	}//getReviewDetail
	
	@Test
	@Order(7)
	@DisplayName("removeReview")
	void testremoveReview() throws ServiceException {
		log.trace("removeReview : 리뷰 삭제 테스트");
		
		int result1 = this.revireService.removeReview(22);
		int result2 = this.revireService.removeReview(300);		
		log.info("result : {}, {}", result1, result2);
	}//removeReview
	
	@Test
	@Order(8)
	@DisplayName("countReview")
	void countReview() throws ServiceException{
		log.trace("countReview : 리뷰 개수 테스트");
				
		Map<String,Object> starAvg = this.revireService.countReview(65);
		log.info("\t + starAvg : {}", starAvg);
						
	}//removeReview
	

}//end class
