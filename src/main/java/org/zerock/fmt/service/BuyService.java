package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.exception.ServiceException;

public interface BuyService {

	
	// 마이페이지 - 구매내역조회 
	public abstract List<BuyVO> myPageBuy(CriteriaMyPage cri) throws ServiceException;
	// 마이페이지 - 구매 총 건수
	public abstract int myPageBuyCount(String user_email) throws ServiceException;
	// 마이페이지 - 구매내역 상세 조회 
	public abstract BuyInfoVO myPageBuyinfo(Integer b_number) throws ServiceException;
	
	
	// 구매하기
	public abstract Integer buy(BuyDTO buyHands) throws ServiceException;
	

	
	// 어드민 - 구매내역 전체 조회 
	public abstract List<BuyVO> selectAllBuy(CriteriaAdmin cri) throws ServiceException;
	// 어드민 - 구매 총 건수
	public abstract int countBuy() throws ServiceException;
	// 어드민 - 총 판매금액 
	public abstract int countSale() throws ServiceException;
} // end class
