package org.zerock.fmt.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.mapper.CommunityMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor


@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations= "file:src/main/webapp/**/spring/**/*-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommuntyMapperTests {
	
	
	@Setter(onMethod_= @Autowired)
	private CommunityMapper cMapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");
		
		assertNotNull(this.cMapper);
		log.info("cMapper: {}", cMapper);
	}
	
	@Disabled
	@Test
	@DisplayName("CommunityMapper.selectAllList() test")
	@Order(1)
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void SelectAllList() throws DAOException {
		log.trace("SelectAllList() invoked.");
		
		List<CommunityDTO> list = this.cMapper.selectAllList();
		assertNotNull(list);
		
		list.forEach(log::info);
		
	}// selectAllList
	
	
	@Disabled
	@Test
	@DisplayName("CommunityMapper.delete() test")
	@Order(2)
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void delete() throws DAOException {
		log.trace(" delete() invoked.");
		
		int bno = 33;
		
		int affectedLines = this.cMapper.delete(bno);
		
		log.info("\t+ affectedLinte:{}", affectedLines);
		
	}//delete
	
//	@Disabled
	@Test
	@DisplayName("CommunityMapper.insert() test")
	@Order(4)
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void insert() throws DAOException {
		log.trace(" insert() invoked.");
		
		CommunityDTO dto = new CommunityDTO();
		
		dto.setFb_title("이건제목");
		dto.setFb_content("이건내용");
		dto.setUser_email("test@gmail.com");
		
		log.info("\t+ 1.dto: {}", dto);
		
		
		int affectedLines = this.cMapper.insert(dto);
		log.info("\t+ 2.affectedLines:{}", affectedLines);
		
		assert affectedLines == 1;
		
	}//insert
	

	@Test
	@DisplayName("CommunityMapper.update() test")
	@Order(5)
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void update() throws DAOException {
		log.trace(" update() invoked.");
		
		CommunityDTO dto = new CommunityDTO();
		dto.setFb_number(1);
		dto.setFb_title("제목수정");
		dto.setFb_content("내용수정");
		
		log.info("\t+ 1.dto: {}", dto);
		
		
		int affectedLines = this.cMapper.update(dto);
		log.info("\t+ 2.affectedLines:{}", affectedLines);
		
		
		assert affectedLines == 1;
		
	}//insert
	
}//end class
