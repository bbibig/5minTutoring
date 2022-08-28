package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CriteriaFaq;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

//마이페이지
public interface MypageService {
	
	//1. 기본정보 조회
	public abstract UserVO getUserInfo(String user_email) throws ServiceException;

	//2. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<QuestionBardVO> getAllMyQuestionList(CriteriaFaq cri) throws ServiceException;
	
	//3. 나의 질문글 목록 총 개수 획득
	public abstract int getMyQuestionTotalAmount() throws ServiceException;
	
}// end interface
