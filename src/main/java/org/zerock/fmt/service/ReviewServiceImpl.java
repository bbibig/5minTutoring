package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.ReviewMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Service
public class ReviewServiceImpl implements ReviewService{

	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;
	
	
	@Override
	public int createReview(ReviewDTO dto) throws ServiceException {
		log.trace("createReview invoked.");
		try {
			return this.reviewMapper.InsertReview(dto);
		}catch(Exception e) { throw new ServiceException(e); }
	}//createReview


	@Override
	public List<ReviewVO> getReview(CriteriaReview cri) throws ServiceException {
		log.trace("getReview invoked.");
		try {
			return this.reviewMapper.selectReview(cri);
		}catch(Exception e) {throw new ServiceException(e);}
	}//getReview


	@Override
	public int countList(int tp_number) throws ServiceException {
		log.trace("countList invoked.");
		try {
			return this.reviewMapper.countList(tp_number);
		}catch(Exception e) {throw new ServiceException(e); }
	}//createReview


	@Override
	public int modifyReview(ReviewDTO dto) throws ServiceException {
		log.trace("modifyReview invoked.");
		try {
			return this.reviewMapper.updateReview(dto);			
		}catch(Exception e) { throw new ServiceException(e); }
	}//modifyReview


	@Override
	public double avgReview(int tp_number) throws ServiceException {
		log.trace("avgReview invoked.");
		
		try {
			if(this.reviewMapper.avgReview(tp_number)==null) {
				this.reviewMapper.updateAve(0.0, tp_number);
				//별점없으면 튜터페이지 별점 0 으로 업데이트
				return 0.0;
			} else {
				double star = this.reviewMapper.avgReview(tp_number);
				int avgResult = this.reviewMapper.updateAve(star, tp_number);
				//튜터페이지 별점 평균별점으로 업데이트
					if(avgResult==0){
						log.info("tutorPage average update fail");
					};
			 return star;
			}//if-else
			
		}catch(Exception e) { throw new ServiceException(e);}
	}//avgReview


	@Override
	public ReviewVO getRevirwDetail(int rv_number) throws ServiceException {
		log.trace("getRevirwDetail.");
		try {
			return this.reviewMapper.selectRVone(rv_number);
		}catch(Exception e) {throw new ServiceException(e); }
	}//getRevirwDetail


	@Override
	public int removeReview(int rv_number) throws ServiceException {
		log.trace("removeReview. ");
		try {
			return this.reviewMapper.deleteReview(rv_number);
		}catch (Exception e) {throw new ServiceException(e);}
	}//removeReview
	
}//end interface
