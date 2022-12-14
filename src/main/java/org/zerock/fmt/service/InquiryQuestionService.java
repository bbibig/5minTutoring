package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.exception.ServiceException;

public interface InquiryQuestionService {
	
//	1:1 문의글 작성 
	public abstract boolean createIQ(InquiryQuestionDTO dto) throws ServiceException; 

	// 관리자
//	1:1 문의글 목록 조회 - 답변 완료
	public abstract List<InquiryQuestionVO> getAllInquiryYList(CriteriaAdmin cri) throws ServiceException; 
	
//	1:1 문의글 목록 조회 - 미 답변
	public abstract List<InquiryQuestionVO> getAllInquiryNList(CriteriaAdmin cri) throws ServiceException; 
	
//	페이징 
	public abstract int countList(String iq_pass) throws ServiceException;
	
//	특정 1:1 문의글 조회 
	public abstract InquiryQuestionVO getInquiry(Integer iq_number) throws ServiceException; 
	
	
//  답변 상태 수정 (답변 대기 / 답변 완료)
	public abstract boolean updateInquiryState(Integer iq_number) throws ServiceException; 
	
}
