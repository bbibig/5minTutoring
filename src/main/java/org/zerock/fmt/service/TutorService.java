package org.zerock.fmt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.TutorPageDTO;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

public interface TutorService {

	// 튜터페이지 정보 조회
	public abstract TutorPageVO getAllTInfo(@Param("num") String tp_number) throws ServiceException;
	
	// 튜터메인 페이지 튜터카드 (최신순 12개행 정렬) 
	public abstract List<TutorPageVO> getRecentTCard() throws ServiceException;
	
	// 튜터메인 페이지 튜터카드 (과목, 누적답변순 or 평점순 12개행 정렬)
	public abstract List<TutorPageVO> getSortedTCard(String subject, String searchType) throws ServiceException;
	
	// 튜터페이지 소개 정보 입력 (경력, 소개제목, 소개내용)
	public abstract boolean createIntroInfo(TutorPageDTO tutorPagedto) throws ServiceException;
	
	// 튜터페이지 소개 정보 수정 
	public abstract boolean updateTInfo(TutorPageDTO tutorPagedto) throws ServiceException;
	
	
} // end interface
