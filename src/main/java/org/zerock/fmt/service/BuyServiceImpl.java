package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.BuyMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class BuyServiceImpl implements BuyService{
	
	@Setter(onMethod_= {@Autowired})
	private BuyMapper buyMapper;
	
	@Override
	public Integer buy(BuyDTO buyHands) throws ServiceException {
		
		try {
			return this.buyMapper.insertBuyHands(buyHands);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public List<BuyVO> selectAllBuy(CriteriaAdmin cri) throws ServiceException {
		log.trace("selectAllBuy 구매내역 전체 조회");
		try{
			List<BuyVO> list = this.buyMapper.selectAllBuy(cri);
			return list;
		} catch( Exception e) {throw new ServiceException(e); } //try-catch
	}//selectAllBuy


	@Override
	public int countBuy() throws ServiceException {
		log.trace("countBuy 총 구매 건수");
		try {
			return this.buyMapper.countAllBuy();
		}catch(Exception e) {throw new ServiceException(e); }//try-catch
	}//countBuy


	@Override
	public int countSale() throws ServiceException {
		log.trace("selectAllSale 총 판매 금액");
		try {			
			return this.buyMapper.selectAllSale();
		}catch(Exception e) { throw new ServiceException(e); }//try-catch
	}//selectAllSale


	@Override
	public List<BuyVO> myPageBuy(CriteriaMyPage cri) throws ServiceException {
		log.trace("myPageBuyinfo 마이페이지 - 구매내역 정보");
		try {
			List<BuyVO> list = this.buyMapper.myPageAllBuy(cri);
			return list;
		}catch(Exception e) {throw new ServiceException(e); }//try-catch
	}//myPageBuyinfo


	@Override
	public int myPageBuyCount(String user_email) throws ServiceException {
		log.trace("myPageBuyCount 마이페이지 - 총 구매내역 건수");
		try {
			return this.buyMapper.myPageBuyCount(user_email);
		}catch(Exception e) {throw new ServiceException(e); }
	}//selectAllSale


	@Override
	public BuyInfoVO myPageBuyinfo(Integer b_number) throws ServiceException {
		log.trace("selectBuyDetail 마이페이지 - 구매내역 상세조회");
		try {
			BuyInfoVO info = this.buyMapper.selectBuyDetail(b_number);
			return info;
		}catch(Exception e) { throw new ServiceException(e); }//try-catch
	}//selectAllSale
	
} // end class
