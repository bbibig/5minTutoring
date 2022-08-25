package org.zerock.fmt.service;

import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.exception.ServiceException;

public interface FileService {

	public abstract Integer createFiles(FileDTO file) throws ServiceException;
	
	public abstract FileVO getFile(String userEmail) throws ServiceException;

}//end 
