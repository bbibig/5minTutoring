package org.zerock.fmt.mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.CriteriaAdmin;
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
public class AdminMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private AdminMapper adminMapper;
	
	@Test 
	@DisplayName("어드민 로그인하기")
	void testloingAdmin() throws DAOException {
		log.info("어드민로그인 테스트");
		
		String ad_id = "admin";
		String ad_pw = "1234";
		AdminVO admin = this.adminMapper.LoginAdmin(ad_id, ad_pw);
		log.info("\t + admin : {}", admin);
	}//testloingAdmin
	
	@Test
	@DisplayName("관리자 전체조회")
	void selectAdmin() throws DAOException {
		log.trace("관리자 전체 조회 테스트");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(1);
		cri.setPagesPerPage(1);
		List<AdminVO> list = this.adminMapper.selectAdmin(cri);
		list.forEach(log::info);
	}//selectAdmin
	
	@Test
	@DisplayName("총 관리자수")
	void countAdmin() throws DAOException {
		log.info("\t + countAdmin");
		int result = this.adminMapper.adminCount();
		log.info("\t + result : {}", result);
	}//countAdmin
}//end class
