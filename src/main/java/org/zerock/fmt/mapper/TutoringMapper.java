package org.zerock.fmt.mapper;

import java.util.List;

import org.zerock.fmt.domain.TutoringBoardDTO;
import org.zerock.fmt.domain.TutoringBoardVO;
import org.zerock.fmt.exception.DAOException;

public interface TutoringMapper {
	
	// 과외하기 질문 등록 - 손들기 5개 사용
	public abstract Integer insertTutoring(TutoringBoardDTO tbDTO) throws DAOException;

	// 과외하기 질문 수정 - 답변 전에만 가능 (손들기 5개 반환)
	public abstract Integer updateTutoring(TutoringBoardDTO tbDTO) throws DAOException;
	
	// 과외하기 질문 삭제 - 답변 전에만 가능 (손들기 5개 반환)
	public abstract Integer deleteTutoring(int tb_number) throws DAOException;
	
	// 과외하기 답변 상태 변경
	public abstract Integer updateTBAnswer(int tb_number) throws DAOException;
	
	// 해당 튜터의 과외하기 질문 리스트 출력
	public abstract List<TutoringBoardVO> selectTBQ(int tp_number) throws DAOException; 
	
	// 해당 과외하기 질문 출력
	public abstract TutoringBoardVO selectOneTBQ(int tb_number) throws DAOException;
	
} // end interface
