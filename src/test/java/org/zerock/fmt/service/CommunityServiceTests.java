//package org.zerock.fmt.service;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.Timeout;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.zerock.fmt.domain.CommunityDTO;
//import org.zerock.fmt.exception.ServiceException;
//
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@NoArgsConstructor
//
//
//@ExtendWith(SpringExtension.class)
//
//@ContextConfiguration(locations= "file:src/main/webapp/**/spring/**/*-context.xml")
//
//
//@TestInstance(Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class CommunityServiceTests {
//
//	@Setter(onMethod_=@Autowired)
//	private CommunityService communityService;
//	
//	
//	@BeforeAll
//	void beforeAll() {
//		log.trace("beforeAll() invoked.");
//		
//		assertNotNull(this.communityService);
//		
//		log.info("\t+ this. communityService: {}", this.communityService);
//	} // beforeAll
//	
//	
//	@Disabled
//	@Test
//	@Order(1)
//	@DisplayName("1. CommunityService.selectAllList() test.")
//	@Timeout(value=3, unit=TimeUnit.SECONDS)
//	void testSelectAllList() throws ServiceException {
//		log.trace("testGetList() invoked.");
//		
//		List<CommunityDTO> list = this.communityService.selectAllList();
//		
//		list.forEach(log::info);
//		
//	} // testSelectAllList
//	
//	
//	@Disabled
//	@Test
//	@Order(2)
//	@DisplayName("2. CommunityService.read() test.")
//	@Timeout(value=3, unit=TimeUnit.SECONDS)
//	void testRead() throws ServiceException {
//		log.trace("testGet() invoked.");
//		
//		int testBno = 77;
//		CommunityDTO dto = new CommunityDTO();
//		dto.setBno(testBno);
//		
//		CommunityDTO dto2 = this.communityService.read(dto);
//		
//		assertNotNull(dto2);
//		log.info("\t+ vo: {}", dto2);
//	} // testRead
//	
//	
//	@Disabled
//	@Test
//	@Order(3)
//	@DisplayName("3. CommunityService.remove() test.")
//	@Timeout(value=3, unit=TimeUnit.SECONDS)
//	void testRemove() throws ServiceException {
//		log.trace("testRemove() invoked.");
//		
//		int testBno = 41;
//		CommunityDTO dto = new CommunityDTO();
//		dto.setBno(testBno);
//		
//		boolean isSuccess = this.communityService.remove(dto);
//		
//		log.info("\t+ isSuccess: {}", isSuccess);
//	} // testRemove
//	
//	
//	@Disabled
//	@Test
//	@Order(4)
//	@DisplayName("4. CommunityService.create() test.")
//	@Timeout(value=3, unit=TimeUnit.SECONDS)
//	void testCreate() throws ServiceException {
//		log.debug("testCreate() invoked.");
//		
//		CommunityDTO dto = new CommunityDTO(null, "새로운 제목", "새로운 내용", "새로운 닉네임", "새로운 댓글 수","새로운 좋아요수","새로운 날짜");
//		
//		if(communityService.create(dto)) {
//			log.info("\t+ New board registered.");
//			
//			log.info("\t+ dto: " + dto);
//		} else {
//			log.info("\t+ No board registered.");
//		} // if-else
//	} // testCreate
//	
////	@Disabled
//	@Test
//	@Order(5)
//	@DisplayName("5. CommunityService.update() test.")
//	@Timeout(value=3, unit=TimeUnit.SECONDS)
//	void testUpdate() throws ServiceException {
//		log.debug("testUpdate() invoked.");
//		
//		CommunityDTO dto = new CommunityDTO(1, "제목수정", "내용수정", "작성자수정");
//		
//		if(communityService.update(dto)) {
//			log.info("\t+ board modified.");
//		} else {
//			log.info("\t+ No board modified.");
//		} // if-else
//	} // testUpdate
//} //end class
