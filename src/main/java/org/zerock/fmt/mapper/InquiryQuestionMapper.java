package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.exception.DAOException;

public interface InquiryQuestionMapper {

	
//	[C]  1:1 문의글 작성 
	public abstract Integer insertIQ(InquiryQuestionDTO dto) throws DAOException; 

	
//	[R]  1:1 문의글 목록 조회 
//       사용자 => 본인이 작성한 게시글만 조회 가능 (문의제목, 날짜, 답변유무)
//       관리자 => 답변 여부로 나눠서 조회 가능 (문의번호, 제목, 작성자, 작성일, 상태)
	@Select("SELECT \r\n"
			+ "    iq_number, user_email, iq_title, iq_content, iq_date, iq_pass\r\n"
			+ "FROM tbl_individual_question")
	public abstract List<InquiryQuestionVO> selectAllInquiryList() throws DAOException;
	
	
//	[R]  특정 1:1 문의글 조회 
	@Select("SELECT * FROM tbl_individual_question WHERE iq_number = #{iq_number}")
	public abstract InquiryQuestionVO select(@Param("iq_number")Integer iq_number) throws DAOException;
	
	
//  [U]  답변 상태 수정 (미답변(1)/답변완료(0)) 
	public abstract Integer updateInquiryState(InquiryQuestionDTO dto) throws DAOException;
	
	
} // end interface
