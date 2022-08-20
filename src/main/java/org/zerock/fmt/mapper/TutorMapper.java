package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.TutorPageDTO;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.DAOException;

public interface TutorMapper {

	// 튜터페이지 정보 조회
	@Select("SELECT /*+ index_desc(tbl_tutor_page) */ * FROM tbl_tutor_page")
	public abstract List<TutorPageVO> selectAllTInfo() throws DAOException;
	
	// 튜터카드 정보 (이름, 소개, 과목, 답변 수, 평점)
	public abstract List<TutorPageVO> selectTCardInfo() throws DAOException;
	
	// 튜터페이지 정보 수정 (경력, 소개제목, 소개내용)
	public abstract Integer updateTInfo(TutorPageDTO tutorPagedto) throws DAOException;
	
	
} // end interface
