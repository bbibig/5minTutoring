package org.zerock.fmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.BuyMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class BuyServiceImpl implements BuyService{
	
	@Setter(onMethod_= {@Autowired})
	private BuyMapper buyMapper;
	
	
	@Override
	public BuyDTO getPayPage(String user_email, Integer h_number) throws ServiceException {
		
		try {			
			return this.buyMapper.selectPayPage(user_email, h_number);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public Integer buy(BuyDTO buyHands) throws ServiceException {
		
		try {
			return this.buyMapper.insertBuyHands(buyHands);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public BuyDTO getMyPayPage(Integer bNum) throws ServiceException {
		
		try {
			return this.buyMapper.selectMyPayPage(bNum);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
} // end class
