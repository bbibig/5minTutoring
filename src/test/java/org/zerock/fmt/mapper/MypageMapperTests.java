package org.zerock.fmt.mapper;

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
import org.zerock.fmt.domain.CriteriaFaq;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class MypageMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private MypageMapper mapper;
	
	
	//1. 기본정보 조회(특정 회원)
	@Test
	@Order(1)
	@DisplayName("1. TestselectUser")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllFaqList() throws DAOException {
		log.trace("TestselectUser(), 마이페이지 기본정보 조회");
		
		UserVO vo = mapper.selectUser("test@gmail.com");
		log.info("\t+ 기본정보 조회 {}", vo);
	}//TestselectUser()
	
	
	//2. 기본정보 수정(학생)
	@Test
	@Order(2)
	@DisplayName("2. TestUpdateStudentInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void TestUpdateStudentInfo() throws DAOException {
		log.trace("TestselectUser(), 마이페이지 기본정보 수정(학생)");
		
		UserDTO dto = new UserDTO("test1@gmail.com", "변경1234", "닉네임", "학생이름", "20040101", "여자",
								   "01012345678", "고등학생", "3학년", null, null, null, null);
		
		boolean result = mapper.updateStudentInfo(dto);
		log.info("\t+ 기본정보 수정결과: {}", result);
		
	}//TestUpdateStudentInfo()
	
	
	//3. 기본정보 수정(튜터)
	@Test
	@Order(3)
	@DisplayName("3. TestupdateTutorInfo")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void TestupdateTutorInfo() throws DAOException {
		log.trace("TestselectUser(), 마이페이지 기본정보 수정(튜터)");
		
		UserDTO dto = new UserDTO("TTtest1@gmail.com", "변경1234", "닉네임", "튜터이름", "19940101", "여자",
								   "01012345678", null, null, "졸업", "국어", "file:파일경로", "국어국문학과");
		
		boolean result = mapper.updateTutorInfo(dto);
		log.info("\t+ 기본정보 수정결과: {}", result);
		
	}//TestupdateTutorInfo()
	
	
	//4. 나의 질문글 목록 조회
	@Test
	@Order(4)
	@DisplayName("4. testSelectAllQuestionList")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testSelectAllQuestionList() throws DAOException {
		log.trace("testSelectAllQuestionList(), 마이페이지 나의 질문글 목록 조회");
		
		CriteriaFaq cri = new CriteriaFaq();
		
		List<QuestionBardVO> list = mapper.selectAllMyQuestionList(cri);
		list.forEach(e -> log.info(e));
		
	}//testSelectAllQuestionList()
	
	//5. 나의 질문글 목록 총 개수
	@Test
	@Order(5)
	@DisplayName("5. testGetMyQuestionTotalAmount")
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	void testGetMyQuestionTotalAmount() throws DAOException {
		log.trace("testSelectAllQuestionList(), 마이페이지 나의 질문글 목록 총 개수 조회");
		
		String user_email = "test@gmail.com";
		
		Integer amount = mapper.getMyQuestionTotalAmount();
		log.info("\t + 나의 질문글 총 개수: {}", amount);
		
	}//testGetMyQuestionTotalAmount()


}// end class
























