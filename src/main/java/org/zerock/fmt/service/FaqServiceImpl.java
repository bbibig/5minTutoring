package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.FaqMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//자주묻는 질문 서비스 계층
@Log4j2
@NoArgsConstructor

@Service
public class FaqServiceImpl implements FaqService {
	
	@Setter(onMethod_= @Autowired)
	private FaqMapper mapper;
	
	
	//1. 자주묻는 질문과 답 목록 전체 조회
	@Override
	public List<FaqVO> getFaqList(CriteriaMyPage cri) throws ServiceException {
		log.trace("getList() 자주묻는 질문 리스트 전체 조회(페이징) 서비스 메소드 호출");
		
		try { return this.mapper.selectAllFaqList(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }

	}// getList()
	
	//2. 자주묻는 질문과 답 생성
	@Override
	public boolean createFaq(FaqDTO dto) throws ServiceException {
		log.trace("createFaq() 자주묻는 질문과 답 생성 서비스 메소드 호출");
		
		try { return this.mapper.insertFaq(dto); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// createFaq
	
	//3. 자주묻는 질문과 답 수정
	@Override
	public boolean updateFaq(FaqDTO dto) throws ServiceException {
		log.trace("createFaq() 자주묻는 질문과 답 수정 서비스 메소드 호출");
		
		try { return this.mapper.updateFaq(dto); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// updateFaq
	
	//4. 자주묻는 질문과 답 삭제
	@Override
	public boolean removeFaq(FaqDTO dto) throws ServiceException {
		log.trace("removeFaq() 자주묻는 질문과 답 삭제 서비스 메소드 호출");
		
		try { return this.mapper.deleteFaq(dto.getFaq_number()); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// removeFaq
	
	//5. 자주묻는 질문과 답 총 게시물 개수 획득
	@Override
	public int getFaqTotal() throws ServiceException {
		log.trace("removeFaq() 자주묻는 질문과 답 총 게시물 개수 획득 서비스 메소드 호출");
		
		try { return this.mapper.getFaqTotalAmount(); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getFaqTotal

}// end class


















