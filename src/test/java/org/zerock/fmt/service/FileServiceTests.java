package org.zerock.fmt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileServiceTests {

	@Setter(onMethod_ = @Autowired)
	private FileService fileService;
	
	@BeforeAll
	void beforeAll() {
		assertNotNull(this.fileService);
		log.info("\t FileService : {}", this.fileService);
	}//
	
	@Test
	@Order(1)
	@DisplayName("FileService. 1. createFiles ")
	void createFilesTest() throws ServiceException {
		log.trace("createFiles Service Test");
		
		FileDTO file = new FileDTO();

		file.setFileName("FILE_NAME1");
		file.setUploadPath("C://TEMP");
		file.setUserEmail("tt@email");
		file.setUuid("1010101");
		int Result = this.fileService.createFiles(file);
		log.info("\t + Result : {}", Result);
		
	}//createFiles
	
	@Test
	@Order(2)
	@DisplayName("FileService. 2. getFile")
	void getFileTest () throws ServiceException {
		log.trace("getFile Service Test");
		
		String userEmail = "tt@email";
		FileVO filevo = this.fileService.getFile(userEmail);
		log.info("\t +_fileVO : {}", filevo);
	}//getFile
}//end class
