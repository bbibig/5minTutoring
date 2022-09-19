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
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CriteriaComment;
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
public class CommentServiceTests {

	@Setter(onMethod_ = @Autowired)
	private CommentService commentService;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.commentService);	
		log.info("\t + 1. this.commentMapper: {}", this.commentService);
		log.info("\t + 2. type: {}", this.commentService.getClass().getName());
	} // injectionTest
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. CommentService.createComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void createCommentTest() throws ServiceException {
		log.trace("댓글 등록");
		
		CommentDTO newComment = new CommentDTO(null, 20, null, "test@email.net", "좋은 답변 갑사합니다.");
	
		boolean createSucceed = this.commentService.createComment(newComment);
		log.info("createSucceed : {}", createSucceed);
		
	} // createCommentTest
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. CommentService.updateComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateCommentTest() throws ServiceException {
		log.trace("댓글 수정");
		
		CommentDTO revisedComment = new CommentDTO(28, null, null, null, "좋은 답변 갑사합니다.(수정)");
		
		boolean reviseSucceed = this.commentService.updateComment(revisedComment);
		log.info("reviseSucceed : {}", reviseSucceed);
		
	} // updateCommentTest
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. CommentService.deleteComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void deleteCommentTest() throws ServiceException {
		log.trace("댓글 삭제");
		
		String cm_number = "28";
		
		boolean deleteSucceed = this.commentService.deleteComment(cm_number);
		log.info("deleteSucceed : {}", deleteSucceed);
		
	} // deleteCommentTest
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. CommentService.getComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void getCommentTest() throws ServiceException {
		log.trace("해당 게시글의 댓글을 출력");
		
		CriteriaComment cri = new CriteriaComment();
		cri.setA_number(20);
		cri.setCurrPage(1);
		cri.setAmount(5);
		
		List<CommentVO> list = this.commentService.getComment(cri);
		list.forEach(log::info);

	} // getCommentTest
	
} // end class
