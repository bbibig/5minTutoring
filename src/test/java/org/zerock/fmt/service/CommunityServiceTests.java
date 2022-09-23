package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
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
import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaCommunity;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor


@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations= "file:src/main/webapp/**/spring/**/*-context.xml")


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommunityServiceTests {

	@Setter(onMethod_=@Autowired)
	private CommunityService communityService;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.communityService);
		
		log.info("\t+ this. communityService: {}", this.communityService);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. CommunityService.selectAllList() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectAllList() throws ServiceException {
		log.trace("testGetList() invoked.");
		CriteriaCommunity page = new CriteriaCommunity();
		List<CommunityVO> list = this.communityService.selectAllList(page);
		
		list.forEach(log::info);
		
	} // testSelectAllList
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. CommunityService.get() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testRead() throws ServiceException {
		log.trace("testGet() invoked.");
		
		int fb_number = 32;
		CommunityDTO dto = new CommunityDTO();
		dto.setFb_number(fb_number);
		
		CommunityVO vo = this.communityService.read(dto);
		
		assertNotNull(vo);
		log.info("\t+ vo:{}", vo);	
	} // testRead
	

//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. CommunityService.remove() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testRemove() throws ServiceException {
		log.trace("testRemove() invoked.");
		
		int fb_number = 2;
		CommunityDTO dto = new CommunityDTO();
		dto.setFb_number(fb_number);
		
		Boolean isSuccess =  this.communityService.remove(dto);

		log.info("\t+ affectedLines:{}", isSuccess); 
	} // testRemove
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. CommunityService.create() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testCreate() throws ServiceException {
		log.debug("testCreate() invoked.");
		
		CommunityDTO dto = new CommunityDTO();
		
		dto.setFb_title("이건제목");
		dto.setFb_content("이건내용");
		dto.setUser_email("test@gmail.com");
		
		if(communityService.create(dto)) {
			log.info("\t+ New board registered.");
			
			log.info("\t+ dto: " + dto);
		} else {
			log.info("\t+ No board registered.");
		} // if-else
		
	} // testCreate
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. CommunityService.update() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdate() throws ServiceException {
		log.debug("testUpdate() invoked.");
		
		CommunityDTO dto = new CommunityDTO();
		
		dto.setFb_number(1);
		dto.setFb_title("이건제목 수정");
		dto.setFb_content("이건내용 수정");
		dto.setUser_email("test@gmail.com");
		
		if(communityService.update(dto)) {
			log.info("\t+ board modified.");
		} else {
			log.info("\t+ No board modified.");
		} // if-else
	} // testUpdate
	
} //end class
