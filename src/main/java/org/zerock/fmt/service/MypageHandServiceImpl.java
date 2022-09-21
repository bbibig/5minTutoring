package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.AnswerVO2;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandQVO;
import org.zerock.fmt.domain.UseHandTVO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.MypageHandMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//마이페이지(손들기 조회)
@Log4j2
@NoArgsConstructor

@Service
public class MypageHandServiceImpl implements MypageHandService {
	
	@Setter(onMethod_= @Autowired)
	private MypageHandMapper mapper;

	
	//1-1. 손들기 사용 목록 조회 페이징 처리(학생) - 질문하기
	@Override
	public List<UseHandQVO> getAllMyUsehandtQList(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 사용 목록 목록 조회 - 질문하기");	
		try { return this.mapper.selectAllmyUsehandQList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyCommentList

	//1-2. 손들기 사용 목록 총 횟수 조회 - 질문하기
	@Override
	public int getMyUsehandQTotalAmount(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 사용 목록 총 횟수 조회 - 질문하기");		
		try { return this.mapper.getMyUsehandQTotalAmount(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyQuestionTotalAmount
	
	//1-3. 손들기 사용 목록 조회 페이징 처리(학생) - 과외받기
	@Override
	public List<UseHandTVO> getAllMyUsehandtTList(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 사용 목록 목록 조회 - 과외받기");	
		try { return this.mapper.selectAllmyUsehandTList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyCommentList

	//1-4. 손들기 사용 목록 총 횟수 조회 - 과외받기
	@Override
	public int getMyUsehandTTotalAmount(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 사용 목록 총 횟수 조회 - 과외받기");		
		try { return this.mapper.getMyUsehandTTotalAmount(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyQuestionTotalAmount
	
	
	//2-1. 손들기 구매 목록 조회 페이징처리(학생)
	@Override
	public List<BuyVO> myPageBuy(CriteriaMyPage cri) throws ServiceException {
		log.trace("myPageBuyinfo 마이페이지 - 구매내역 정보");
		try {
			List<BuyVO> list = this.mapper.myPageAllBuy(cri);
			return list;
		}catch(Exception e) {throw new ServiceException(e); }//try-catch
	}//myPageBuy

	//2-2. 손들기 구매 내역 총 수량
	@Override
	public Integer myPageBuyCount(CriteriaMyPage cri) throws ServiceException {
		log.trace("myPageBuyCount 마이페이지 - 총 구매내역 건수");
		try {
			return this.mapper.myPageBuyCount(cri);
		}catch(Exception e) {throw new ServiceException(e); }
	}//myPageBuyCount

	//2-3 손들기 구매내역 상세 조회
	@Override
	public BuyInfoVO myPageBuyinfo(Integer b_number) throws ServiceException {
		log.trace("selectBuyDetail 마이페이지 - 구매내역 상세조회");
		try {
			BuyInfoVO info = this.mapper.selectBuyDetail(b_number);
			return info;
		}catch(Exception e) { throw new ServiceException(e); }//try-catch
	}//myPageBuyinfo
	
	
	//3-1 손들기 출금 내역 목록 조회 페이징 처리(내림차순으로) 
	@Override
	public List<WithdrawalVO> getAllMyWithdrawalList(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 출금 내역 목록 조회");	
		try { return this.mapper.selectAllMyWithdrawalList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
	}// getAllMyWithdrawalList
	
	//3-2 손들기 출금 내역 총 수량
	@Override
	public int getMyWithdrawalTotalAmount(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 출금 내역 총 횟수 조회");		
		try { return this.mapper.getMyWithdrawalTotalAmount(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
	}// getMyWithdrawalTotalAmount
	
	
	//4-1 손들기 획득 내역 목록(질문하기) 조회 페이징 처리(튜터) 
	public List<AnswerVO2> getAllmyGetHandQList(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 획득 내역 목록 조회");	
		try { return this.mapper.selectAllmyGetHandQList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
	}// getAllmyGetHandQList
		
	//4-2 손들기 획득 내역(질문하기) 총 횟수
	public Integer getMyGetHandQTotalAmount(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 출금 내역 총 횟수 조회");		
		try { return this.mapper.getMyGetHandQTotalAmount(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
	}// getMyGetHandQTotalAmount
	
	
	//5. 튜터페이지 조회
	public Integer getTutorPageNum(String user_email) throws ServiceException {
		try { return this.mapper.getTutorPageNum(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
	}// getTutorPageNum
	
}// end class


















