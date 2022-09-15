package org.zerock.fmt.mapper;

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
public class CommentMapperTests {

	@Setter(onMethod_ = @Autowired)
	private CommentMapper commentMapper;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.commentMapper);	
		log.info("\t + 1. this.commentMapper: {}", this.commentMapper);
		log.info("\t + 2. type: {}", this.commentMapper.getClass().getName());
	} // injectionTest
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. CommentMapper.insertComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void insertCommentTest() throws DAOException {
		log.trace("댓글 등록");
		
		CommentDTO newComment = new CommentDTO(null, 20, null, "test@email.net", "좋은 답변 갑사합니다.");
	
		int affectedLines = this.commentMapper.insertComment(newComment);
		
		log.info("newComment : {}", newComment);
		log.info("affectedLines : {}", affectedLines);
		
	} // insertCommentTest
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. CommentMapper.updateComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void updateCommentTest() throws DAOException {
		log.trace("댓글 수정");
		
		CommentDTO revisedComment = new CommentDTO(21, null, null, null, "좋은 답변이네요.(수정)");
		
		int affectedLines = this.commentMapper.updateComment(revisedComment);
		
		log.info("revisedComment : {}", revisedComment);
		log.info("affectedLines : {}", affectedLines);
		
	} // updateCommentTest
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. CommentMapper.deleteComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void deleteCommentTest() throws DAOException {
		log.trace("댓글 삭제");
		
		String cm_number = "21";
		
		int affectedLines = this.commentMapper.deleteComment(cm_number);
		log.info("affectedLines : {}", affectedLines);
		
		assert affectedLines == 1;
		
	} // deleteCommentTest
	
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. CommentMapper.selectComment() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void selectCommentTest() throws DAOException {
		log.trace("댓글 출력");
		
		String a_number = "20";
		
		List<CommentVO> list = this.commentMapper.selectComment(a_number);
		list.forEach(log::info);
		
	} // selectCommentTest
	
} // end class
