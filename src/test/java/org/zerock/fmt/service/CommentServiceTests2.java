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
import org.zerock.fmt.domain.CommentVO2;
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
				
		int fb_nubmer = 38;
		List<CommentVO2> list = this.commentService2.readComment(fb_nubmer);
		list.forEach(log::info);

	} // getCommentTest
}