package org.zerock.fmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.FileMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
@Service
public class FileServiceImpl implements FileService{

	@Setter(onMethod_ = @Autowired)
	private FileMapper fileMapper;
	
	@Override
	public Integer createFiles(FileDTO file) throws ServiceException {
		log.trace("createFiles() service test");
		
		try{
			int Result = this.fileMapper.insertFile(file);
			log.info("\t Result : {}", Result);
			return Result;
		} catch ( Exception e ) {throw new ServiceException(e); }
	}//createFiles

	
	@Override
	public FileVO getFile(String userEmail) throws ServiceException {
		log.trace("getFile() service test");
		
		try{ 
			FileVO filevo = this.fileMapper.selectFile(userEmail); 
			log.info("\t + FileVO : {}", filevo);
			return filevo;
		} catch(Exception e) { throw new ServiceException(e); }
		
	}//getFile

}
