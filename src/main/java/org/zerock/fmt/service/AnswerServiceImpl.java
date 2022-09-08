package org.zerock.fmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.fmt.domain.AnswerDTO;
import org.zerock.fmt.domain.AnswerVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.AnswerMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Setter(onMethod_ = @Autowired)
	private AnswerMapper answerMapper;
	
	@Transactional
	@Override
	public boolean createA(AnswerDTO newA) throws ServiceException {
		log.trace("답변 등록 및 답변 상태 변경");
		
		String qb_number = Integer.toString(newA.getQb_number());
		
		try {
			int createResult = this.answerMapper.insertA(newA);
			
			// 답변이 등록되면 답변 등록 여부 업데이트
			if(createResult == 1) { 
				this.answerMapper.updateAStatus(qb_number); 
				return true;
				
			} else { log.info("답변이 등록되지 않았습니다."); }
			return false;
			
		} catch (Exception e) { throw new ServiceException(e); }
	} // createA

	
	@Override
	public boolean updateA(AnswerDTO Avo) throws ServiceException {
		log.trace("답변 수정");
		
		// 이메일이 일치하면 답변 수정 가능
		try { return this.answerMapper.updateA(Avo) == 1; } 
		catch (Exception e) { throw new ServiceException(e); }
	} // updateA

	
	@Override
	public AnswerVO getA(String qb_number) throws ServiceException {
		log.trace("해당 질문에 대한 답변 출력");
		
		try { return this.answerMapper.selectA(qb_number); } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // getA
	
} // end class
