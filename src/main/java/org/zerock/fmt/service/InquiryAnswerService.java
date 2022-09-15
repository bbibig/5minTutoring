package org.zerock.fmt.service;

import org.zerock.fmt.domain.InquiryAnswerDTO;
import org.zerock.fmt.domain.InquiryAnswerVO;
import org.zerock.fmt.exception.ServiceException;

public interface InquiryAnswerService {

	
//	1:1 문의글 답변 작성
	public abstract boolean createIA(InquiryAnswerDTO dto) throws ServiceException; 
	
		
//	특정 1:1 문의글 답변 조회 
	public abstract InquiryAnswerVO getIA(Integer iq_number) throws ServiceException; 

} // end interface
 