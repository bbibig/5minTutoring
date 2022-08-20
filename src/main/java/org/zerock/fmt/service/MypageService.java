package org.zerock.fmt.service;

import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

//마이페이지
public interface MypageService {
	
	//1. 기본정보 조회
	public abstract UserVO getUserInfo(String user_email) throws ServiceException;

}// end interface
