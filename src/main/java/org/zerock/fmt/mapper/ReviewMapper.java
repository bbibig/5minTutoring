package org.zerock.fmt.mapper;

import java.util.List;

import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.DAOException;

public interface ReviewMapper {

	//리뷰 등록
	public abstract int InsertReview(ReviewDTO dto) throws DAOException;
	
	//리뷰 조회 
	public abstract List<ReviewVO> selectReview(CriteriaReview cri) throws DAOException;
	
	//페이징 
	public abstract int countList(Integer tp_number) throws DAOException;
	
	//리뷰 존재 유무
//	public abstract int checkReview(Integer tp_number) throws DAOException;
	
	//리뷰 수정
	public abstract int updateReview(ReviewDTO dto) throws DAOException;
	
	//리뷰 평균
	public abstract Double avgReview(Integer tp_number) throws DAOException;
	
}//end 
