package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO;
import org.zerock.fmt.exception.DAOException;

public interface AskMapper {

	// 질문글 저장
	public abstract Integer insertQ(QuestionBoardVO QBvo) throws DAOException;
	
	// 질문글 수정 
	public abstract Integer updateQ(QuestionBoardVO QBvo) throws DAOException;
	
	// 질문글 삭제 - 답변 전 삭제 => 손들기 복구 / 답변 후 삭제 => X
	public abstract Integer deleteQ(int qb_number) throws DAOException;
	
	// 해당 튜터페이지의 질문글 출력
	public abstract List<QuestionBoardVO> selectQB(int tp_number) throws DAOException; 
	
} // end class
