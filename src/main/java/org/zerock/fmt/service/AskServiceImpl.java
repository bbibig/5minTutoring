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
import org.zerock.fmt.mapper.ReviewMapper;
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
	private AnswerMapper answerMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UseHandMapper useHandMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;
	

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
			
		} catch (Exception e) { throw new ServiceException(e); }
	} // createQ
	
	@Transactional
	@Override
	public boolean regUseHand(UseHandVO useHandVO) throws ServiceException {
		log.trace("손들기 사용정보 저장");
		
		try { return this.useHandMapper.insertUseHand(useHandVO) == 1; } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // regUseHand
	
	
	// 질문글 등록, 손들기 사용 / 손들기 사용정보 저장을 Transaction propagation 처리
	@Transactional
	public boolean questionTransaction(QuestionBoardDTO QBdto, UseHandVO useHandVO) throws ServiceException {
		log.trace("questionTransaction invoked.");
		
		String studentEmail = QBdto.getUser_email();
		useHandVO.setUser_email(studentEmail);
		
		boolean result_1 = createQ(QBdto);
		boolean result_2 = regUseHand(useHandVO);
		
		if(result_1 && result_2) {
			log.info("질문글 등록 & 손들기 정보 저장 완료!");
			return true;
		}
		
		return false;
		
	} // questionTransaction
	
	
	
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
			
		} catch (Exception e) { throw new ServiceException(e); } 
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
		} catch (Exception e) { throw new ServiceException(e); }

		// 3일 이상 답변이 없었던 경우 반환 - 자동으로 삭제

	} // deleteQ

	@Override
	public List<QuestionBoardVO> getQB(String tp_number) throws ServiceException {
		log.trace("해당 튜터가 받은 질문글 출력");
		
		try { return this.askMapper.selectQB(tp_number); } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // getQB


	@Override
	public QuestionBoardVO getOneQ(String qb_number) throws ServiceException {
		log.trace("질문글 번호로 질문글 출력");
		
		try { return this.askMapper.selectOneQ(qb_number); } 
		catch (Exception e) { throw new ServiceException(e); }
		
	}//getOneQ

	@Override
	public boolean answerCountAndReview(Integer tp_number,String user_email) throws ServiceException {
		log.trace("QeustionAndReview: 답변개수가 리뷰글보다 많을시 리뷰작성 가능 여부");
		try{
			int anwserCount = this.askMapper.countQeustion(tp_number,user_email);
			int myRvCount = this.reviewMapper.countMyReivew(tp_number, user_email);
			
			// 답변글개수 > 리뷰개수 
			if(anwserCount>myRvCount) {
				return true;
			} 
			// 답변글만큼 리뷰가 적혀져있으면 리뷰적을수 없음 
			return false;
		}catch(Exception e) {throw new ServiceException(e);}
		
	} // countQeustion

} // end class
