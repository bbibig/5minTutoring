package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.exception.ServiceException;

public interface HandService {

	// 구매하기 - 전체 상품 조회
	public abstract List<HandVO> getAllHands() throws ServiceException;
	
	// 구매하기 - 상품별 조회
	public abstract HandVO getHand(Integer hNum)throws ServiceException;
	
	
} // end interface
