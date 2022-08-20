package org.zerock.fmt.service;

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
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
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
public class MypageServiceTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageService service;
	
	
	//1. 기본정보 테스트
	@Test
	@Order(1)
	@DisplayName("1. TestGetUserInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetFaqList() throws ServiceException {
		log.trace("TestGetUserInfo(), 마이페이지 기본정보 조회 테스트");
		
		String user_email = "test@gmail.com";
		
		UserVO vo = this.service.getUserInfo(user_email);
		log.info("\t+ 기본정보: {}", vo);
		
	}//TestGetUserInfo()

}// end class
























