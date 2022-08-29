package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.ServiceException;

//자주묻는 질문 서비스 계층
public interface FaqService {
	
	//1. 자주묻는 질문과 답 목록 전체 조회
//	public abstract List<FaqVO> getFaqList() throws ServiceException;
	public abstract List<FaqVO> getFaqList(CriteriaMyPage cri) throws ServiceException;

	//2. 자주묻는 질문과 답 생성
	public abstract boolean createFaq(FaqDTO dto) throws ServiceException;
	
	//3. 자주묻는 질문과 답 수정
	public abstract boolean updateFaq(FaqDTO dto) throws ServiceException;
	
	//4. 자주묻는 질문과 답 삭제
	public abstract boolean removeFaq(FaqDTO dto) throws ServiceException;
	
	//5. 게시판의 총 게시물 개수 획득
	public abstract int getFaqTotal() throws ServiceException;
	
}// end interface
