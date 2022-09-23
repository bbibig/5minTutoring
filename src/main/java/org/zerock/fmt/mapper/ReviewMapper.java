package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.DAOException;

public interface ReviewMapper {

	//리뷰 등록
	public abstract int InsertReview(ReviewDTO dto) throws DAOException;
	
	//리뷰 조회 + 정렬
	public abstract List<ReviewVO> selectReview(CriteriaReview cri) throws DAOException;
	
	//페이징 
	public abstract int countList(Integer tp_number) throws DAOException;
	
	//리뷰 존재 유무
//	public abstract int checkReview(Integer tp_number) throws DAOException;
	
	//리뷰 수정
	public abstract int updateReview(ReviewDTO dto) throws DAOException;
	
	//리뷰 조회(1)
	public abstract ReviewVO selectRVone(Integer rv_number) throws DAOException;
	
	//리뷰 평균
	public abstract Double avgReview(Integer tp_number) throws DAOException;
	
	//튜터테이블 평균 업데이트
	public abstract int updateAve(@Param("tp_average")Double tp_average,
								  @Param("tp_number")Integer tp_number) throws DAOException;
	//리뷰 삭제 
	public abstract int deleteReview(Integer rv_number) throws DAOException;
	
	//별점 개수 구하기
//	public abstract Map<String,Object> countReview(Integer tp_number) throws DAOException;
	public abstract int countReview(@Param("rv_star")Integer rv_star, @Param("tp_number")Integer tp_number) throws DAOException;
	
	
}//end 
