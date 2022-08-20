package org.zerock.fmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//마이페이지
@Log4j2
@NoArgsConstructor

@Service
public class MypageServiceImpl implements MypageService {
	
	@Setter(onMethod_= @Autowired)
	private MypageMapper mapper;


	@Override
	public UserVO getUserInfo(String user_email) throws ServiceException {
		log.trace("getUserInfo() 기본정보 조회");
		
		try { return this.mapper.selectUser(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }

	}// getList()

}// end class


















