package org.zerock.fmt.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
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
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileMapperTests {

	@Setter(onMethod_ = @Autowired)
	private FileMapperTest fileMapper;
	
	@BeforeAll
	void beforeAll() {
		assertNotNull(this.fileMapper);
		log.info("\t + this. FileMapper : {}", this.fileMapper);
	}//before
	
	
	@Test
	@Order(1)
	@DisplayName("FileMapper. 1. InserFile")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void insertFiletest() throws DAOException {
		log.info("insertFile test");
		 
		FileDTO dto = new FileDTO();
		dto.setFileName("F_NAME_2");
		dto.setUploadPath("C:TEMP/");
		dto.setUuid("00001100010");
		dto.setUserEmail("newTT");
		
		int Result = this.fileMapper.insertFile(dto);
		log.info("\t + File insert Result : {}", Result);
		
	}//insertFile
	
	@Test
	@Order(2)
	@DisplayName("FileMapper. 2. selectFile")
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void selectFileTest() throws DAOException {
		log.trace("selectFile test");

		String user_email = "newTT";
		FileVO filevo = this.fileMapper.selectFile(user_email);
		log.info("\t + fileVO : {}", filevo);
	}//selectFile
}//end class
