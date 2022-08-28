package org.zerock.fmt.service;

import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.ServiceException;

public interface BuyService {

	// 구매하기 - 구매 정보 페이지
	public abstract BuyDTO getPayPage(String user_email, Integer h_number) throws ServiceException;
	
	
	// 마이페이지 - 구매내역 조회
	public abstract BuyDTO getMyPayPage(Integer bNum ) throws ServiceException;
	
	
	// 구매하기
	public abstract Integer buy(BuyDTO buyHands) throws ServiceException;
	
	
	
} // end class
