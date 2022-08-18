package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.DAOException;

//자주묻는 질문
public interface FaqMapper {
	
	//1. 자주묻는 질문 목록 전체 조회
	@Select("SELECT /*+ index_desc(test_faq)*/ * FROM tbl_faq")
	public abstract List<FaqVO> selectAllList() throws DAOException;
	

}// end interface
