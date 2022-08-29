package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.DAOException;

//자주묻는 질문과 답
public interface FaqMapper {
	
//	============<<SELECT>>============
	//1-1. 자주묻는 질문과 답 목록 전체 조회(내림차순으로)
//	@Select("SELECT /*+ index_desc(tbl_faq)*/ * FROM tbl_faq")
//	public abstract List<FaqVO> selectAllFaqList() throws DAOException;
	
	//1-2. 자주묻는 질문과 답 목록 전체 조회 페이징 처리(내림차순으로)
	public abstract List<FaqVO> selectAllFaqList(CriteriaMyPage cri) throws DAOException;
	
	//2. 자주묻는 질문과 답 목록 총 개수 조회
	@Select("SELECT count(faq_number) FROM tbl_faq")
	public abstract Integer getFaqTotalAmount() throws DAOException;
	
//	============<<INSERT>>============
	//2. 자주묻는 질문과 답 생성
	public abstract boolean insertFaq(FaqDTO dto) throws DAOException;
	
//	============<<UPDATE>>============
	//3. 자주묻는 질문과 답 수정
	public abstract boolean updateFaq(FaqDTO dto) throws DAOException;
	
//	============<<DELETE>>============
	//4. 자주묻는 질문과 답 삭제
	@Delete("DELETE FROM tbl_faq WHERE faq_number = #{faq_number}")
	public abstract boolean deleteFaq(@Param("faq_number") Integer faq_number) throws DAOException;

}// end interface
