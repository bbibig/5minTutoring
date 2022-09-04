package org.zerock.fmt.service;

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
public class AdminServiceTests {

	@Setter(onMethod_ = @Autowired)
	private AdminService adminService;
	
	@Test
	@DisplayName("Admin Login")
	void adminLogin() throws ServiceException {
		log.trace("Admin Login");
		String ad_id = "admin";
		String ad_pw = "1234";
		AdminVO admin = this.adminService.Login(ad_id, ad_pw);
		log.info("\t + Login Admin: {}", admin);

	}//adminLogin
	
	@Test
	@DisplayName("Admin List")
	void adminAllList() throws ServiceException {
		log.trace("\t + Admin List");
		CriteriaAdmin cri = new CriteriaAdmin();
		cri.setAmount(5);
		cri.setCurrPage(2);
		List<AdminVO> list = this.adminService.adminList(cri);
		list.forEach(log::info);
	}//adminAllList
	
	@Test
	@DisplayName("adminCount")
	void adminCount() throws ServiceException {
		log.trace("adminCount");
		int result = this.adminService.adminCount();
		log.info("\t +result : {}", result);
	}//adminCount
	

}//end class
