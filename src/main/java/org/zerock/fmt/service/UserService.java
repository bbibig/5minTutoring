package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

public interface UserService {

	public abstract List<UserVO> getAllUser() throws ServiceException;
	
	public abstract UserVO getUserInfo(String user_email) throws ServiceException;
	
	public abstract boolean singUpStrudent(UserDTO newStudent) throws ServiceException;
	
	public abstract boolean singUPTutor(UserDTO newTutor) throws ServiceException;
	
//	public abstract void singUPTutor(UserDTO newTutor, 
//										MultipartHttpServletRequest mpRequest) throws ServiceException;
	
	public abstract boolean updateUser(UserDTO user) throws ServiceException;
	
	public abstract boolean tutorPass(String user_email) throws ServiceException;
	
	public abstract boolean userStatus(String user_email) throws ServiceException;
}//end 
