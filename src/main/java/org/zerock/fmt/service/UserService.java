package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

public interface UserService {

	//조회
	public abstract List<UserVO> getAllUser() throws ServiceException;
	
	public abstract UserVO getUserInfo(String user_email) throws ServiceException;
	
	public abstract Integer getNicCheck(String newNick) throws ServiceException;
	
	//추가 
	public abstract boolean singUpStrudent(UserDTO newStudent) throws ServiceException;
	
	public abstract boolean singUPTutor(UserDTO newTutor) throws ServiceException;
	
	//수정
	public abstract boolean updateUser(UserDTO user) throws ServiceException;
	
	public abstract boolean tutorPass(String user_email) throws ServiceException;
	
	public abstract boolean userStatus(String user_email) throws ServiceException;
	
	//수정(손들기)
	public abstract boolean updateHandGet(Integer h_count, String user_email) throws ServiceException;

	public abstract boolean updateHandUse(Integer h_count, String user_email) throws ServiceException;
}//end 
