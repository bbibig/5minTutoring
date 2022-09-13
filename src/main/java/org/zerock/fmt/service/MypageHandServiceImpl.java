package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandVO2;
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

	
	//1-1. 손들기 사용 목록 조회 페이징 처리(학생)
	@Override
	public List<UseHandVO2> getAllMyUsehandtList(CriteriaMyPage cri) throws ServiceException {
		log.trace("손들기 사용 목록 목록 조회");
		
		try { return this.mapper.selectAllmyUsehandList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyCommentList

	//1-2. 손들기 사용 목록 총 횟수 조회
	@Override
	public int getMyUsehandTotalAmount(String user_email) throws ServiceException {
		log.trace("손들기 사용 목록 총 횟수 조회");
		
		try { return this.mapper.getMyUsehandTotalAmount(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyQuestionTotalAmount
}// end class


















