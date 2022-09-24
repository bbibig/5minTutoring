package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.fmt.domain.HandBackVO;
import org.zerock.fmt.domain.TutoringBoardDTO;
import org.zerock.fmt.domain.TutoringBoardVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.TutoringMapper;
import org.zerock.fmt.mapper.UseHandMapper;
import org.zerock.fmt.mapper.UserMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class TutoringServiceImpl implements TutoringService {
	
	@Setter(onMethod_ = @Autowired)
	private TutoringMapper tutoringMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UseHandMapper useHandMapper;

	@Transactional
	@Override
	public boolean createTutoring(TutoringBoardDTO tbDTO) throws ServiceException {
		log.trace("과외하기 질문 등록 및 손들기 사용");
		
		try {
			int remainingHand = this.useHandMapper.selectHandsWallet(tbDTO.getUser_email());

			// 남은 손들기가 5개 이상이면 손들기 사용 (-5)
			if (remainingHand >= 5) {
				this.userMapper.updateHandUse(5, tbDTO.getUser_email());
				log.info("손들기 5개가 사용되었습니다.");
				
				// 과외하기 질문 등록
				return this.tutoringMapper.insertTutoring(tbDTO) == 1;
				
			} else { log.info("손들기가 부족합니다."); }
			return false;
			
		} catch (Exception e) { throw new ServiceException(e); }
	} // createTutoring

	@Transactional
	@Override
	public boolean updateTutoring(TutoringBoardDTO tbDTO) throws ServiceException {
		log.trace("과외하기 질문 수정");
		
		try {
			TutoringBoardVO tutoringVO = this.tutoringMapper.selectOneTBQ(tbDTO.getTb_number());
			int answer = tutoringVO.getTb_answer();
			
			// 과외하기 완료 전에만 질문 수정이 가능
			if(answer == 0) { return this.tutoringMapper.updateTutoring(tbDTO) == 1; }
			else { log.info("과외하기가 완료되어 질문을 수정할 수 없습니다."); }
			return false;
			
			} catch (Exception e) { throw new ServiceException(e); } 
	} // updateTutoring

	@Transactional
	@Override
	public boolean deleteTutoring(int tb_number) throws ServiceException {
		log.trace("과외하기 질문 삭제");
		
		try {
			TutoringBoardVO tutoringVO = this.tutoringMapper.selectOneTBQ(tb_number);
			int answer = tutoringVO.getTb_answer();
			String user_email = tutoringVO.getUser_email();
			
			// 과외하기 완료 전에만 질문 삭제가 가능
			if(answer == 0) { 
				// 손들기 반환
				this.userMapper.updateHandGet(5, user_email);
				
				// 손들기 반환 정보 저장
				HandBackVO handBackVO = new HandBackVO(null, null, tb_number, null, user_email);
				this.useHandMapper.insertHandBack(handBackVO);
				
				log.info("손들기 5개가 반환되었습니다.");
				
				// tbl_tutoring_board와 tbl_usehand_student의 
				// 질문글과 손들기 사용정보 연쇄삭제
				return this.tutoringMapper.deleteTutoring(tb_number) == 1;
				
			} else { log.info("과외하기가 완료되어 질문을 삭제할 수 없습니다."); }
			return false;
			
		} catch (Exception e) { throw new ServiceException(e); } 
	} // deleteTutoring

	@Override
	public boolean updateTBAnswer(int tb_number) throws ServiceException {
		log.trace("답변 완료 상태 변경");
		
		try { return this.tutoringMapper.updateTBAnswer(tb_number) == 1; } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // updateTBAnswer

	@Override
	public List<TutoringBoardVO> getTBQ(int tp_number) throws ServiceException {
		log.trace("과외하기 질문 리스트 출력");
		
		try { return this.tutoringMapper.selectTBQ(tp_number); } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // getTBQ

	@Override
	public TutoringBoardVO getOneTBQ(int tb_number) throws ServiceException {
		log.trace("과외하기 질문 출력");
		
		try { return this.tutoringMapper.selectOneTBQ(tb_number); } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // updateTBAnswer

} // end class
