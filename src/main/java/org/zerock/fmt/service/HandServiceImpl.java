package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.HandMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class HandServiceImpl implements HandService {
	
	@Setter(onMethod_= {@Autowired})
	private HandMapper handMapper;
	
	@Override
	public List<HandVO> getAllHands() throws ServiceException{
		
		try {
			return this.handMapper.selectAllHands();
		} catch( Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HandVO getHand(Integer hNum) throws ServiceException {

		try {			
			return this.handMapper.selectHand(hNum);
		} catch (Exception e) {
			throw new ServiceException(e);
		} 
		
	} //getHand



} // end class
