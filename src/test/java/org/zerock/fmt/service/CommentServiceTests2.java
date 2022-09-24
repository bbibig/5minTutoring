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
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/**/spring/**/*-context.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentServiceTests2 {

	@Setter(onMethod_ = @Autowired)
	private CommentService2 commentService2;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.commentService2);	
		log.info("\t + 1. this.commentMapper: {}", this.commentService2);
		log.info("\t + 2. type: {}", this.commentService2.getClass().getName());
	} // injectionTest
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. CommentService2.readComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testReadComment() throws ServiceException {
		log.trace("testReadComment() invoked");
				
		int fb_nubmer = 56;
		List<CommentVO2> list = this.commentService2.readComment(fb_nubmer);
		list.forEach(log::info);

	} // ReadCommentTest
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. CommentService2.writeComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testWriteComment() throws ServiceException {
		log.trace("writeComment() invoked");
				
		CommentDTO dto = new CommentDTO();
		dto.setFb_number(56);
		dto.setUser_email("seosujung0@gmail.com");
		dto.setCm_content("댓글테스트4");
		
		if(commentService2.writeComment(dto)) {
			log.info("\t+ New Comment registered.");
			
			log.info("\t+ dto: " + dto);
		} else {
			log.info("\t+ No board registered.");
		} // if-else

	} // ReadCommentTest
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. CommentService2.updateComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testUpdateComment() throws ServiceException {
		log.trace("updateComment() invoked");
				
		CommentDTO dto = new CommentDTO();
		dto.setCm_number(119);
		dto.setCm_content("댓글 수정test");
		
		
		if(commentService2.updateComment(dto)) {
			log.info("\t+ board modified.");
		} else {
			log.info("\t+ No board modified.");
		} // if-else

	} // updateCommentTest
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. CommentService2.deleteComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testDeleteComment() throws ServiceException {
		log.trace("deleteComment() invoked");
				
		CommentDTO dto = new CommentDTO();
		dto.setCm_number(123);
		
		boolean isResult = commentService2.deleteComment(dto);
		log.info("isResutl:{}",isResult);
		
	} // deleteCommentTest
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. CommentService2.selectComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectComment() throws ServiceException {
		log.trace("selectComment() invoked");
				
		int cm_number = 124;
		CommentDTO dto = new CommentDTO();
		dto.setCm_number(cm_number);
		
		CommentVO2 vo2=this.commentService2.selectComment(dto);
		log.info("vo2:{}", vo2);
		
	} // selectComment
	
	
	
	
	
}//end class