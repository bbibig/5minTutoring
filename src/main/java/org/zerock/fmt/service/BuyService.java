package org.zerock.fmt.service;

import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.ServiceException;

public interface BuyService {

	// 구매하기 - 구매 정보 페이지
	public abstract BuyDTO getPayPage(String user_email, Integer h_number) throws ServiceException;
	
	
} // end class
