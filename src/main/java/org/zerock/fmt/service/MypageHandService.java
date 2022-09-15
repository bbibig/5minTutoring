package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.exception.ServiceException;

//마이페이지(손들기 조회)
public interface MypageHandService {
	
	//1-1. 손들기 사용 목록 조회 페이징 처리(학생) - 질문하기
	public abstract List<UseHandVO2> getAllMyUsehandtQList(CriteriaMyPage cri) throws ServiceException;
	
	//1-2. 손들기 사용 목록 총 횟수 조회 - 질문하기
	public abstract int getMyUsehandQTotalAmount(CriteriaMyPage cri) throws ServiceException;
	
	//1-3. 손들기 사용 목록 조회 페이징 처리(학생) - 과외받기
	public abstract List<UseHandVO2> getAllMyUsehandtTList(CriteriaMyPage cri) throws ServiceException;
	
	//1-4. 손들기 사용 목록 총 횟수 조회 - 과외받기
	public abstract int getMyUsehandTTotalAmount(CriteriaMyPage cri) throws ServiceException;
	
	
	//2-1. 손들기 구매 목록 조회 페이징처리(학생)
	public abstract List<BuyVO> myPageBuy(CriteriaMyPage cri) throws ServiceException;
	
	//2-2. 손들기 구매 내역 총 수량
	public abstract Integer myPageBuyCount(CriteriaMyPage cri) throws ServiceException;
	
	//2-3 손들기 구매내역 상세 조회
	public abstract BuyInfoVO myPageBuyinfo(Integer b_number) throws ServiceException;
	
}// end interface
