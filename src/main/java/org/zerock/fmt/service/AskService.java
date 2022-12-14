package org.zerock.fmt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.QuestionBoardDTO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

public interface AskService {
	
	// 질문글 작성
	public abstract boolean createQ(QuestionBoardDTO QBdto) throws ServiceException;
	
	// 손들기 사용정보 저장
	public abstract boolean regUseHand(UseHandVO useHandVO) throws ServiceException;
	
	// 질문글 작성과 손들기 사용정보 저장 트랜잭션 전파 처리
	public boolean questionTransaction(QuestionBoardDTO QBdto, UseHandVO userHandVO) throws ServiceException;
	
	// 질문글 수정 - 튜터 답변 전까지만 가능
	public abstract boolean updateQ(QuestionBoardDTO QBdto) throws ServiceException;
	
	// 질문글 삭제 - 답변 전 삭제 => 손들기 복구 / 답변 후 삭제 => X
	public abstract boolean deleteQ(String qb_number, String user_email) throws ServiceException;
	
	// 해당 튜터페이지의 질문글 출력
	public abstract List<QuestionBoardVO> getQB(String tp_number) throws ServiceException; 
	
	// 질문글 번호로 질문글 출력
	public abstract QuestionBoardVO getOneQ(String qb_number) throws ServiceException;
	
	// 질문글-리뷰글 개수 비교 
	public abstract boolean answerCountAndReview(Integer tp_number, String user_email) throws ServiceException;
	
} // end interface
