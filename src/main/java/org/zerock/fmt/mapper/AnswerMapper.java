package org.zerock.fmt.mapper;

import java.util.List;

import org.zerock.fmt.domain.AnswerVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.exception.DAOException;

public interface AnswerMapper {

	// 답변글 작성
	public abstract Integer insertA(AnswerVO Avo) throws DAOException;
	
	// 답변글 수정 
	public abstract Integer updateA(AnswerVO Avo) throws DAOException;
	
	// 해당 질문에 대한 답변글 출력
	public abstract AnswerVO selectA(String qb_number) throws DAOException; 
	// 튜터 이름도 같이 출력할지 고민,,
	
	// 답변 상태 변경 - QB_ANSWER 컬럼의 값 1로 변경
	public abstract Integer updateAStatus(String qb_number) throws DAOException;
	
} // end interface
