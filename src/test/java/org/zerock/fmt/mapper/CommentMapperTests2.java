package org.zerock.fmt.mapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/**/spring/**/*-context.xml"})

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentMapperTests2 {

	@Setter(onMethod_ = @Autowired)
	private CommentMapper2 commentMapper2;
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. CommentMapper2.readComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testReadComment() throws DAOException {
		log.trace("readCommnet() invoked.");
		
		int fb_number = 32;
		List<CommentVO2> list = this.commentMapper2.readComment(fb_number);
		list.forEach(log::info);
		
	} // testReadComment
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. CommentMapper2.writeComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testWriteComment() throws DAOException {
		log.trace("writeComment() invoked.");
		
		CommentDTO dto= new CommentDTO();
		dto.setFb_number(56);
		dto.setUser_email("seosujung0@gmail.com");
		dto.setCm_content("테스트댓글3");
		
		log.info("\t+ 1.dto: {}", dto);
		int isResult = this.commentMapper2.writeComment(dto);
		log.info("isResult:{}", isResult);
		
		
	} // selectCommentTest
	
	
	
	
}// end class
