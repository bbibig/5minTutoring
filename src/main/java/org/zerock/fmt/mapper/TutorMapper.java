package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.TutorException;

public interface TutorMapper {

	// 튜터페이지 정보 조회
	@Select("SELECT /*+ index_desc(tbl_tutor_page) */ * FROM tbl_tutor_page")
	public abstract List<TutorPageVO> selectAllTInfo() throws TutorException;
	
	// 튜터카드 정보
	public abstract List<TutorPageVO> selectTCardInfo() throws TutorException;
	
	// 튜터페이지 학력, 경력, 소개 수정 (DTO,,)
	public abstract List<TutorPageVO> updateTInfo() throws TutorException;
	
	
} // end interface
