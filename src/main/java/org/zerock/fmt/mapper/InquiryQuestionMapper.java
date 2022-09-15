package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.exception.DAOException;

public interface InquiryQuestionMapper {

	
//		[C]  1:1 문의글 작성 
		public abstract Integer insertIQ(InquiryQuestionDTO dto) throws DAOException; 

		
//		[R]  1:1 문의글 목록 조회 - 답변 완료 (관리자 페이지)
		public abstract List<InquiryQuestionVO> selectAllInquiryYList(CriteriaMyPage cri) throws DAOException;
		
//		[R]  1:1 문의글 목록 조회 - 미 답변 (관리자 페이지)
		public abstract List<InquiryQuestionVO> selectAllInquiryNList(CriteriaMyPage cri) throws DAOException;
		
//		[R]  특정 1:1 문의글 조회 
		public abstract InquiryVO selectInquiry(@Param("iq_number")Integer iq_number) throws DAOException;
		
		
	//  [U]  답변 상태 수정 (미답변(1)/답변완료(0)) 
		public abstract Integer updateInquiryState(InquiryQuestionDTO dto) throws DAOException;
		
		
	} // end interface
