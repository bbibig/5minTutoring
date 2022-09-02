package org.zerock.fmt.mapper;


import org.zerock.fmt.domain.InquiryAnswerDTO;
import org.zerock.fmt.exception.DAOException;


public interface InquiryAnswerMapper {

	// 1:1 문의답변은 수정(U)/삭제(D) 없음  (작성만 가능)  
	
	
//	[C]  1:1 문의글 답변 작성
	public abstract int insertIA(InquiryAnswerDTO dto) throws DAOException; 
	

	

	
} // end interface
