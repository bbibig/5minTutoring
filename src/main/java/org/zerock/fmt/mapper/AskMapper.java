package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.QuestionBoardDTO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.exception.DAOException;

public interface AskMapper {

	// 질문글 저장
	public abstract Integer insertQ(QuestionBoardDTO QBdto) throws DAOException;
	
	// 질문글 수정 
	public abstract Integer updateQ(QuestionBoardDTO QBdto) throws DAOException;
	
	// 질문글 삭제 - 답변 전 삭제 => 손들기 복구 / 답변 후 삭제 => X
	public abstract Integer deleteQ(String qb_number) throws DAOException;
	
	// 해당 튜터페이지의 질문글 출력
	public abstract List<QuestionBoardVO> selectQB(String tp_number) throws DAOException; 
	
	// 질문글 출력
	public abstract QuestionBoardVO selectOneQ(String qb_number) throws DAOException;
	
	// 답변완료된 질문글 개수 
	public abstract int countQeustion(@Param("tp_number")Integer tp_number, 
									  @Param("user_email")String user_email) throws DAOException;
	
} // end class
