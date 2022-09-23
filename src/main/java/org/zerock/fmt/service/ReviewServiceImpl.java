package org.zerock.fmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.ProfileMapper;
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
	
	@Setter(onMethod_ = @Autowired)
	private ProfileMapper profileMapper;
	
	
	@Override
	public int createReview(ReviewDTO dto) throws ServiceException {
		log.trace("createReview invoked.");
		try {
			return this.reviewMapper.InsertReview(dto);
		}catch(Exception e) { throw new ServiceException(e); }
	}//createReview


//	@Override
//	public List<ReviewVO> getReview(CriteriaReview cri) throws ServiceException {
//		log.trace("getReview invoked.");
//		try {
//			return this.reviewMapper.selectReview(cri);
//		}catch(Exception e) {throw new ServiceException(e);}
//	}//getReview
	@Override
	public List<ReviewVO> getReview(CriteriaReview cri) throws ServiceException {
		log.trace("getReview invoked.");
		try {
			List<ReviewVO> list = this.reviewMapper.selectReview(cri);
			list.forEach( e ->{
				
				List<ProfileVO> proVO;
				try {
					proVO = this.profileMapper.selectProfile(e.getUser_email());
					if(proVO != null) {
						e.setProfile(proVO);
					} else {
						e.setProfile(null);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				log.info("\t+ ReviewList for ProfileVO : {}", e);
			});//for
			return list;
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


	@Override
	public Map<String,Object> countReview(Integer tp_number) throws ServiceException {
		try {		
			
			HashMap<String,Object> starAvg = new HashMap<>();
			
			//각 별점마다 개수 획득
			int star5 = this.reviewMapper.countReview(5,tp_number);
			int star4 = this.reviewMapper.countReview(4,tp_number);
			int star3 = this.reviewMapper.countReview(3,tp_number);
			int star2 = this.reviewMapper.countReview(2,tp_number);
			int star1 = this.reviewMapper.countReview(1,tp_number);
			
			//총 리뷰개수 획득
			int totalStar = this.reviewMapper.countList(tp_number);
			log.info("총리뷰개수: {}",totalStar);
			
			if(totalStar<=10) {
				starAvg.put("star5", (double)star5/totalStar*100);
				starAvg.put("star4", (double)star4/totalStar*100);
				starAvg.put("star3", (double)star3/totalStar*100);
				starAvg.put("star2", (double)star2/totalStar*100);
				starAvg.put("star1", (double)star1/totalStar*100);
				log.info("10개이하 : {}, ",starAvg);
				
			} else {
				
				starAvg.put("star5", (double)star5*totalStar);
				starAvg.put("star4", (double)star4*totalStar);
				starAvg.put("star3", (double)star3*totalStar);
				starAvg.put("star2", (double)star2*totalStar);
				starAvg.put("star1", (double)star1*totalStar);
				log.info("10개이상: {}", starAvg);
			}	

			return starAvg;
		}catch(Exception e) {throw new ServiceException(e); }
	}//removeReview
	
}//end interface
