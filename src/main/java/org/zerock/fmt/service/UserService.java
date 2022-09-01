package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;




public interface UserService {

	//--------조회
	//모든유저조회
	public abstract List<UserVO> getAllUser() throws ServiceException;
	
	//유저정보조회
	public abstract UserVO getUserInfo(String user_email) throws ServiceException;
	
	//닉네임 중복 체크
	public abstract Integer getNicCheck(String newNick) throws ServiceException;
	
	//로그인
	public abstract UserVO loginEmail(String user_email) throws ServiceException;
	public abstract UserVO gettLoginUser(String user_email,String user_pw) throws ServiceException;
	
	//비밀번호찾기
	public String findPassword(UserDTO user) throws ServiceException;
	
	//------- 추가
	//학생회원가입
	public abstract boolean singUpStrudent(UserDTO newStudent) throws ServiceException;
	
	//튜터회원가입
	public abstract boolean singUPTutor(UserDTO newTutor) throws ServiceException;
	
	//--------수정
	//유저정보수정
	public abstract boolean updateUser(UserDTO user) throws ServiceException;
	
	//튜터승인
	public abstract boolean tutorPass(String user_email) throws ServiceException;
	
	//회원탈퇴
	public abstract boolean userStatus(String user_email) throws ServiceException;
	
	
	//-------수정
	//손들기 추가
	public abstract boolean updateHandGet(Integer h_count, String user_email) throws ServiceException;
	//손들기 차감
	public abstract boolean updateHandUse(Integer h_count, String user_email) throws ServiceException;
}//end 
