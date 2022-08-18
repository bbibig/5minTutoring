package org.zerock.fmt.service;

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
	
	
	//1. 자주묻는 질문 목록 전체 조회 테스트
	@Test
	@Order(1)
	@DisplayName("1. testGetList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetList() throws DAOException, ServiceException {
		log.trace("testGetList(), 자주묻는 질문 리스트 전체 조회");
		
		List<FaqVO> list = this.service.getList();
		list.forEach(e -> log.info(e));
		
	}//testGetList()

}// end class
























