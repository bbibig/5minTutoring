package org.zerock.fmt.service;

import org.zerock.fmt.domain.AnswerDTO;
import org.zerock.fmt.domain.AnswerVO;
import org.zerock.fmt.exception.ServiceException;

public interface AnswerService {
	
	// 답변 등록 및 답변 상태 변경 
	public abstract boolean createA(AnswerDTO newA) throws ServiceException;
	
	// 답변 수정 
	public abstract boolean updateA(AnswerDTO Avo) throws ServiceException;
	
	// 해당 질문에 대한 답변 출력
	public abstract AnswerVO getA(String qb_number) throws ServiceException; 
	
	
} // end interface