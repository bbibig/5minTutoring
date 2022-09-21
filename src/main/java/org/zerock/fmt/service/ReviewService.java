package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.ServiceException;

public interface ReviewService {

	public abstract int createReview(ReviewDTO dto) throws ServiceException;
	
	public abstract List<ReviewVO> getReview(CriteriaReview cri) throws ServiceException;
	
	public abstract int countList(int tp_number)throws ServiceException;
	
	public abstract int modifyReview(ReviewDTO dto) throws ServiceException;
	
	public abstract ReviewVO getRevirwDetail(int rv_number)throws ServiceException;
	
	public abstract double avgReview(int tp_number) throws ServiceException;
	
	public abstract int removeReview(int rv_number) throws ServiceException;
	
}//end interface
