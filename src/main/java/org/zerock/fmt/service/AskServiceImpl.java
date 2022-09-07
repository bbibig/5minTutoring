package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.fmt.domain.AnswerVO;
import org.zerock.fmt.domain.HandBackVO;
import org.zerock.fmt.domain.QuestionBoardDTO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.AnswerMapper;
import org.zerock.fmt.mapper.AskMapper;
import org.zerock.fmt.mapper.UseHandMapper;
import org.zerock.fmt.mapper.UserMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class AskServiceImpl implements AskService {

	@Setter(onMethod_ = @Autowired)
	private AskMapper askMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UseHandMapper useHandMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AnswerMapper answerMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;

	@Transactional
	@Override
	public boolean createQ(QuestionBoardDTO QBdto) throws ServiceException {
		log.trace("질문글 등록 및 손들기 사용");
		
		try {
			int remainingHand = this.useHandMapper.selectHandsWallet(QBdto.getUser_email());

			// 남은 손들기가 3개 이상이면 손들기 사용 (-3)
			if (remainingHand >= 3) {
				this.userMapper.updateHandUse(3, QBdto.getUser_email());
				log.info("손들기 3개가 사용되었습니다.");
				
				// 질문글 등록
				return this.askMapper.insertQ(QBdto) == 1;
				
			} else { log.info("손들기가 부족합니다."); }
			return false;
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try-catch
	} // createQ
	
	@Override
	public boolean regUseHand(UseHandVO useHandVO) throws ServiceException {
		log.trace("손들기 사용정보 저장");
		
		try { return this.useHandMapper.insertUseHand(useHandVO) == 1; } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	} // regUseHand

	@Transactional
	@Override
	public boolean updateQ(QuestionBoardDTO QBdto) throws ServiceException {
		log.trace("질문글 수정");
		
		try {
			AnswerVO answer = this.answerMapper.selectA( Integer.toString(QBdto.getQb_number()) );
			
			// 답변글이 없을 때만 질문글 수정 가능
			if(answer == null) { return this.askMapper.updateQ(QBdto) == 1; } 
			else { log.info("답변이 등록되어 질문을 수정할 수 없습니다."); }
			return false;
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try-catch
	} // updateQ

	@Transactional
	@Override
	public boolean deleteQ(String qb_number, String user_email) throws ServiceException {
		log.trace("질문글 삭제");

		try {
			AnswerVO answer = this.answerMapper.selectA(qb_number);
			
			// 답변글이 없을 때만 손들기 반환 및 삭제 가능
			if(answer == null) {
				// 손들기 반환
				this.userMapper.updateHandGet(3, user_email);
				
				// 손들기 반환 정보 저장
				HandBackVO handBackVO = new HandBackVO(null, Integer.parseInt(qb_number), null, null, user_email);
				this.useHandMapper.insertHandBack(handBackVO);

				log.info("손들기 3개가 반환되었습니다.");
				
				// tbl_question_board와 tbl_usehand_student의 
				// 질문글과 손들기 사용정보 연쇄삭제
				return this.askMapper.deleteQ(qb_number) == 1;
				
			} else { log.info("답변이 등록되어 질문을 삭제할 수 없습니다."); }
			
			return false;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} // try-catch

		// 3일 이상 답변이 없었던 경우 반환 - 자동으로 삭제..
		// tutoring에서 과외 취소 시에도 복구

	} // deleteQ

	@Override
	public List<QuestionBoardVO> getQB(String tp_number) throws ServiceException {
		log.trace("해당 튜터가 받은 질문글 출력");
		
		try { return this.askMapper.selectQB(tp_number); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	} // getQB

} // end class
