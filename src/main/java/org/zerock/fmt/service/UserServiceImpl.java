package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.UserMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class UserServiceImpl implements UserService{

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
//------------------------------------------------------------
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		log.info("\t proxy 객체 만들어졌습니까");
//		Objects.requireNonNull(this.userMapper);
//		log.info("\t + userMapper : {}", this.userMapper);
//		
//	}//afterPropertiesSet
//------------------------------------------------------------

	@Override
	public List<UserVO> getAllUser() throws ServiceException {
		log.trace("getAllUser() invoked.");
		
		try{ return this.userMapper.selectAllUser();}
		catch(Exception e) { throw new ServiceException(e); }
		
	}//getAllUser

	@Override
	public UserVO getUserInfo(String user_email) throws ServiceException {
		log.trace("getUserInfo() invoked.");	
		
		try { return this.userMapper.selectUser(user_email);}
		catch(Exception e) { throw new ServiceException(e);}
		
	}//getUserInfo

	@Override
	public boolean singUpStrudent(UserDTO newStudent) throws ServiceException {
		log.trace("singUpStrudent() invoked.");
		
		try { return this.userMapper.insertStudent(newStudent)==1;}
		catch(Exception e) { throw new ServiceException(e);}
	}//singUpStrudent

	@Override
	public boolean singUPTutor(UserDTO newTutor) throws ServiceException {
		log.trace("singUPTutor() invoked.");
		
		try { 
			return this.userMapper.insertTutor(newTutor)==1;
//			List<Map<String,Object>> list = fileUtils.uploadFile(newTutor, mpRequest);
			
		}catch(Exception e) { throw new ServiceException(e);}
	}//singUPTutor

	@Override
	public boolean updateUser(UserDTO user) throws ServiceException {
		log.trace("updateUser() invoked");
		
		try { return this.userMapper.updateUser(user) ==1 ;}
		catch(Exception e) { throw new ServiceException(e); }
		
	}//updateUser

	
	@Override
	public boolean tutorPass(String user_email) throws ServiceException {
		log.trace("tutorPass(0 invoked.");
		
		try { return this.userMapper.updateTutorPass(user_email)==1;}
		catch(Exception e) {throw new ServiceException(e);}
	}//tutorPass
	

	@Override
	public boolean userStatus(String user_email) throws ServiceException {
		log.trace("userStatus() invoked.");
		
		try { return this.userMapper.updateUserStop(user_email)==1; }
		catch(Exception e) { throw new ServiceException(e); }
	}//userStatus

	@Override
	public boolean updateHandGet(Integer h_count, String user_email) throws ServiceException {
		log.trace("updateHandGet() 손들기 구매, 손들기 획득");

		try{ return this.userMapper.updateHandGet(h_count, user_email)==1;}
		catch(Exception e) { throw new ServiceException(e); }
		
	}//updateHandGet

	@Override
	public boolean updateHandUse(Integer h_count, String user_email) throws ServiceException {
		log.trace("updateHandUse() 손들기 사용(차감)");
		
		try{ return this.userMapper.updateHandGet(h_count, user_email)==1;}
		catch( Exception e) { throw new ServiceException(e); }
		
	}//updateHandUse

	
	@Override
	public Integer getNicCheck(String newNick) throws ServiceException {
		log.trace("selectNicCheck() 닉네임 중복 조회");
		
		try {			
			 return this.userMapper.selectNicCheck(newNick);
		} catch (Exception e) { throw new ServiceException(e); }
	}//selectNicCheck


//------------------------------------------------------------
}//end class
