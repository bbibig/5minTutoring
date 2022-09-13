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
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.UserDTO;
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
public class MypageHandMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageHandMapper mapper;
	
	//1. 손들기 사용 목록 조회
	@Test
	@Order(1)
	@DisplayName("1. selectAllmyUsehandList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void selectAllmyUsehandList() throws DAOException {
		log.trace("selectAllmyUsehandList(), 마이페이지 손들기 사용 목록 조회");
		
		CriteriaMyPage cri = new CriteriaMyPage();
		cri.setUser_email("test@gmail.com");
		
		List<UseHandVO2> list = mapper.selectAllmyUsehandList(cri);
		list.forEach(e -> log.info(e));

	}//selectAllmyUsehandList()
	
	//2. 손들기 사용 목록 총 개수
	@Test
	@Order(2)
	@DisplayName("2. getMyUsehandTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void getMyUsehandTotalAmount() throws DAOException {
		log.trace("testGetMyCommentTotalAmount(), 마이페이지 손들기 사용 목록 총 개수 조회");
		
		UserDTO dto = new UserDTO();
		dto.setUser_email("test@gmail.com");
		
		Integer amount = mapper.getMyUsehandTotalAmount(dto.getUser_email());
		log.info("\t + 손들기 사용 총 횟수: {}", amount);
		
	}//getMyUsehandTotalAmount()

}// end class






















