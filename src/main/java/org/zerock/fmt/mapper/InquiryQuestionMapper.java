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
	// 매퍼xml에 작성하기..
	public abstract Integer insertSelectKey(InquiryQuestionDTO dto) throws DAOException;
	
	
//	[R]  1:1 문의글 목록 조회 
//       사용자 => 본인이 작성한 게시글만 조회 가능 (문의제목, 날짜, 답변유무)
//       관리자 => 답변 여부로 나눠서 조회 가능 (문의번호, 제목, 작성자, 작성일, 상태)
	@Select("SELECT \r\n"
			+ "    iq_number, user_email, iq_title, iq_content, iq_date, iq_pass\r\n"
			+ "FROM tbl_individual_question")
	public abstract List<InquiryQuestionVO> selectAllInquiryList() throws DAOException;
	
	
//	[R] 특정 1:1 문의글 조회 (만약에 답변이 달렸다면 답변도 함께 조회)
	// xml에 작성하기
	public abstract InquiryQuestionDTO select(@Param("iq_number")Integer iq_number) throws DAOException;
	
	
//  [U] 답변 상태 수정 (미답변/답변완료) 
	// XML에 작성
	public abstract Integer updateInquiryState(InquiryQuestionDTO dto) throws DAOException;
	
	
} // end interface
