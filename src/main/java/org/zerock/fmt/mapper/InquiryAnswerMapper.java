package org.zerock.fmt.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.InquiryAnswerDTO;
import org.zerock.fmt.domain.InquiryAnswerVO;
import org.zerock.fmt.exception.DAOException;

public interface InquiryAnswerMapper {
	
// 	1:1 문의답변은 수정(U)/삭제(D) 없음 
	
//	[C]  1:1 문의글 답변 작성
	public abstract Integer insertIA(InquiryAnswerDTO dto) throws DAOException; 
	
		
//	[R]  특정 1:1 문의글 답변 조회 
	@Select("SELECT * FROM tbl_individual_answer WHERE iq_number = #{iq_number}")
	public abstract InquiryAnswerVO select(@Param("iq_number")Integer iq_number) throws DAOException;
	

	
} // end interface
