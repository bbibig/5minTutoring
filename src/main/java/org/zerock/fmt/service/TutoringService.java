package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.TutoringBoardDTO;
import org.zerock.fmt.domain.TutoringBoardVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

public interface TutoringService {
	
	// 과외하기 질문 등록 - 손들기 5개 사용
	public abstract boolean createTutoring(TutoringBoardDTO tbDTO) throws ServiceException;

	// 과외하기 질문 수정 - 답변 전에만 가능 (손들기 5개 반환)
	public abstract boolean updateTutoring(TutoringBoardDTO tbDTO) throws ServiceException;
	
	// 과외하기 질문 삭제 - 답변 전에만 가능 (손들기 5개 반환)
	public abstract boolean deleteTutoring(int tb_number) throws ServiceException;
	
	// 과외하기 답변 상태 변경
	public abstract boolean updateTBAnswer(int tb_number) throws ServiceException;
	
	// 해당 튜터의 과외하기 질문 리스트 출력
	public abstract List<TutoringBoardVO> getTBQ(int tp_number) throws ServiceException; 
	
	// 해당 과외하기 질문 출력
	public abstract TutoringBoardVO getOneTBQ(int tb_number) throws ServiceException;
	
} // end interface